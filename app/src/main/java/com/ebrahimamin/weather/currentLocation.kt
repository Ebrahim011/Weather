package com.ebrahimamin.weather

import android.content.Context
import android.content.SharedPreferences

object CurrentLocation {
    private const val PREFS_NAME = "location_prefs"
    private const val KEY_LATITUDE = "latitude"
    private const val KEY_LONGITUDE = "longitude"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveLocation(latitude: Double, longitude: Double) {
        sharedPreferences.edit().apply {
            putString(KEY_LATITUDE, latitude.toString())
            putString(KEY_LONGITUDE, longitude.toString())
            apply()
        }
    }

    fun getLocation(): Pair<Double, Double>? {

        val latitude = sharedPreferences.getString(KEY_LATITUDE, null)?.toDoubleOrNull()
        val longitude = sharedPreferences.getString(KEY_LONGITUDE, null)?.toDoubleOrNull()
        return if (latitude != null && longitude != null) {
            Pair(latitude, longitude)
        } else {
            null
        }
    }
}