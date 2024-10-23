// app/src/main/java/com/ebrahimamin/weather/searchFragment.kt
package com.ebrahimamin.weather

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ebrahimamin.weather.api_files.APIs
import com.ebrahimamin.weather.api_files.Location
import com.ebrahimamin.weather.api_files.WeatherResponse
import com.ebrahimamin.weather.api_files.topCities
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class searchFragment : Fragment() {

    private lateinit var searchCityEditText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var cityAdapter: CityAdapter
    private val weatherData = mutableMapOf<String, WeatherResponse>()
    private lateinit var apiService: APIs
    private var searchJob: Job? = null
    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Handle fragment arguments if needed
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Search"
        searchCityEditText = view.findViewById(R.id.searchCity)
        recyclerView = view.findViewById(R.id.recyclerRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        cityAdapter = CityAdapter(topCities.toMutableList()) { location ->
            val coordinates = location.location.split(",")
            val latitude = coordinates[0].toDouble()
            val longitude = coordinates[1].toDouble()
            CurrentLocation.saveLocation(latitude, longitude)
            findNavController().popBackStack()
        }
        backButton = view.findViewById(R.id.backButton)
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        recyclerView.adapter = cityAdapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(APIs::class.java)

        searchCityEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    s?.let {
                        delay(300) // Debounce time in milliseconds
                        filterCities(it.toString())
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        return view
    }

    private fun filterCities(query: String) {
        val filteredCities = topCities.filter { it.cityName.contains(query, ignoreCase = true) }
        fetchWeatherData(filteredCities)
    }

    private fun fetchWeatherData(cities: List<Location>) {
        lifecycleScope.launch {
            for (city in cities) {
                val response = apiService.getWeather(city.location, "1")
                if (response.isSuccessful) {
                    response.body()?.let {
                        weatherData[city.location] = it
                    }
                }
            }
            cityAdapter = CityAdapter(cities.toMutableList()) { location ->
                val coordinates = location.location.split(",")
                val latitude = coordinates[0].toDouble()
                val longitude = coordinates[1].toDouble()
                CurrentLocation.saveLocation(latitude, longitude)
                findNavController().popBackStack()
            }
            recyclerView.adapter = cityAdapter
        }
    }
}