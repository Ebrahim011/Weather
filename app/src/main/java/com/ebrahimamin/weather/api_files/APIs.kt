package com.ebrahimamin.weather.api_files

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {
    @GET("reverse")
    suspend fun getCity(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("format") format: String = "geocodejson"
    ): Response<CityResponse>

    @GET("v1/forecast.json")
    suspend fun getWeather(
        @Query("q") location: String, // Latitude and Longitude (Decimal degree) e.g: q=48.8567,2.3508
        @Query("days") days: String,
        @Query("key") key: String = "73f6f710b61847a08ff53632240910"
    ): Response<WeatherResponse>
}