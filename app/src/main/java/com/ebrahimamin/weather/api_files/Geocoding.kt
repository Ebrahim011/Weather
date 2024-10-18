package com.ebrahimamin.weather.api_files

data class Geocoding(
    val accuracy: Int,
    val admin: Admin,
    val country: String,
    val country_code: String,
    val label: String,
    val name: String,
    val osm_id: Long,  // Changed from Int to Long
    val osm_key: String,
    val osm_type: String,
    val osm_value: String,
    val place_id: Long,  // Changed from Int to Long
    val type: String
)