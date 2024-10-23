package com.ebrahimamin.weather

import DailyAdapter
import android.annotation.SuppressLint
import android.content.Intent
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ebrahimamin.weather.activities.MainActivity
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
    lateinit var hourlyRecyclerView: RecyclerView
    private lateinit var apiService: APIs
    lateinit var dailyRecyclerView: RecyclerView
    lateinit var windSpeedTextView: TextView  // Renamed for clarity
    lateinit var windDirectionTextView: TextView  // Added TextView for wind direction
    lateinit var humidityTextView: TextView  // Added TextView for current humidity
    lateinit var avgHumidityTextView: TextView  // Added TextView for average humidity
    lateinit var cityByGPS:ImageButton

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
        hourlyRecyclerView = view.findViewById(R.id.hourlyRecyclerView)
        hourlyRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        windSpeedTextView = view.findViewById(R.id.windSpeedtv)  // Initialize wind speed TextView
        windDirectionTextView = view.findViewById(R.id.windDirectiontv)  // Initialize wind direction TextView
        humidityTextView = view.findViewById(R.id.humiditytv)  // Initialize current humidity TextView
        avgHumidityTextView = view.findViewById(R.id.humidityAvg)  // Initialize average humidity TextView
        cityByGPS = view.findViewById(R.id.byLocation)

        cityByGPS.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
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
                val response = apiService.getWeather(locationString, "5")
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

        // Display wind data
        windSpeedTextView.text = "Speed: ${weatherResponse.current.wind_kph} km/h"
        windDirectionTextView.text = "Direction: ${weatherResponse.current.wind_dir}"

        // Display humidity data
        humidityTextView.text = "Humidity: ${weatherResponse.current.humidity}%"
        avgHumidityTextView.text = "Avg Humidity: ${weatherResponse.forecast.forecastday[0].day.avghumidity}%"

        // Show notification with city name and temperature
        val notificationHelper = NotificationHelper(requireContext())
        notificationHelper.showNotification(
            weatherResponse.location.name, // The name of the city (weather location)
            weatherResponse.current.temp_c.toString() // The current temperature in Celsius
        )
        // Set up the hourly forecast RecyclerView
        val hourlyAdapter = HourlyAdapter(weatherResponse.forecast.forecastday[0].hour)
        hourlyRecyclerView.adapter = hourlyAdapter

        // Set up the daily forecast RecyclerView
        dailyRecyclerView = view?.findViewById(R.id.dailyRecyclerView) ?: return
        val dailyAdapter = DailyAdapter(weatherResponse.forecast.forecastday)
        dailyRecyclerView.adapter = dailyAdapter
        dailyRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}