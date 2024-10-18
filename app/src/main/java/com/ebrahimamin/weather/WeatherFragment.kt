// app/src/main/java/com/ebrahimamin/weather/WeatherFragment.kt
package com.ebrahimamin.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ebrahimamin.weather.api_files.APIs
import com.ebrahimamin.weather.api_files.WeatherResponse
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherFragment : Fragment() {
    lateinit var cityNameTextView: TextView
    lateinit var degreeTextView: TextView
    lateinit var weatherIconImageView: ImageView
    lateinit var tosearchFragment: ImageButton
    private lateinit var apiService: APIs

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityNameTextView = view.findViewById(R.id.locationName)
        degreeTextView = view.findViewById(R.id.temperature)
        weatherIconImageView = view.findViewById(R.id.weatherIcon)
        tosearchFragment = view.findViewById(R.id.toSearchFragment)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(APIs::class.java)

        fetchWeatherData()
        tosearchFragment.setOnClickListener {
            findNavController().navigate(R.id.action_weaterFragment_to_searchFragment)
        }
    }

    private fun fetchWeatherData() {
        val location = CurrentLocation.getLocation()
        if (location != null) {
            val (latitude, longitude) = location
            val locationString = "$latitude,$longitude"
            lifecycleScope.launch {
                val response = apiService.getWeather(locationString, "1")
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    weatherResponse?.let {
                        updateUI(it)
                    }
                }
            }
        } else {
            // Handle the case where location is not available
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(weatherResponse: WeatherResponse) {
        cityNameTextView.text = weatherResponse.location.name
        degreeTextView.text = "${weatherResponse.current.temp_c}"
        Glide.with(this)
            .load("https:${weatherResponse.current.condition.icon}")
            .into(weatherIconImageView)
    }
}