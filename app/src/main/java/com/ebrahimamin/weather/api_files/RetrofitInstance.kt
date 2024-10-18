package com.ebrahimamin.weather.api_files

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Base URL should not include query parameters
    private const val CITY_BASE_URL = "https://nominatim.openstreetmap.org/"
    private const val WEATHER_BASE_URL = "https://api.weatherapi.com/v1/"

    // City API instance
    val cityApi: APIs by lazy {
        Retrofit.Builder()
            .baseUrl(CITY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIs::class.java)
    }

    // Weather API instance
    val weatherApi: APIs by lazy {
        Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIs::class.java)
    }
}