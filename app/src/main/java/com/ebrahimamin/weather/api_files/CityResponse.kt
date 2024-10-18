package com.ebrahimamin.weather.api_files



data class CityResponse(
    val features: List<Feature>,
    val geocoding: GeocodingX,
    val type: String
)