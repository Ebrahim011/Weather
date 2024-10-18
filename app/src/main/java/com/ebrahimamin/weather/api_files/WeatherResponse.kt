package com.ebrahimamin.weather.api_files

import com.example.weather.api_files.Location

data class WeatherResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)