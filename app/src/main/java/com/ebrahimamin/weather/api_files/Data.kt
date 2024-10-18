package com.ebrahimamin.weather.api_files

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class Data {

    suspend fun getCity(lat: String, lon: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.cityApi.getCity(lat, lon)
                if (response.isSuccessful) {
                    val cityResponse = response.body()
                    val cityName = cityResponse?.features?.firstOrNull()?.properties?.geocoding?.name
                    val locationName = cityResponse?.features?.firstOrNull()?.properties?.geocoding?.label
                    val governmentName = cityResponse?.features?.firstOrNull()?.properties?.geocoding?.admin?.level4

                    when {
                        !cityName.isNullOrEmpty() -> cityName
                        !locationName.isNullOrEmpty() -> locationName
                        !governmentName.isNullOrEmpty() -> governmentName
                        else -> "Unknown Location"
                    }
                } else {
                    "Unknown Location"
                }
            } catch (e: IOException) {
                Log.e("Data", "Error fetching city data", e)
                "Unknown Location"
            } catch (e: Exception) {
                Log.e("Data", "Unexpected error", e)
                "Unknown Location"
            }
        }
    }

    suspend fun getWeather(cityName: String, days: String): WeatherResponse {
        return withContext(Dispatchers.IO) {
            RetrofitInstance.weatherApi.getWeather(cityName, days).body()!!
        }
    }
}