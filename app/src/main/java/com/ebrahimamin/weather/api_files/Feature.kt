package com.ebrahimamin.weather.api_files


data class Feature(
    val geometry: Geometry,
    val properties: Properties,
    val type: String
)