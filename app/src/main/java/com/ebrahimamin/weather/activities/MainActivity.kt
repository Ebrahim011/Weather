// app/src/main/java/com/ebrahimamin/weather/activities/MainActivity.kt
package com.ebrahimamin.weather.activities

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.ebrahimamin.weather.CurrentLocation
import com.ebrahimamin.weather.NotificationHelper
import com.ebrahimamin.weather.WeatherNotificationReceiver
import com.ebrahimamin.weather.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var notificationHelper: NotificationHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Set up the notification helper and create the notification channel
        notificationHelper = NotificationHelper(this)
        notificationHelper.createNotificationChannel()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CurrentLocation.init(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            getLastLocation()
        }

        lifecycleScope.launch {
            delay(2000)
            // Start the weather notification alarm to send notifications periodically
            startWeatherNotificationAlarm()
            if (CurrentLocation.getLocation() == null) {
                showLocationErrorDialog()
            } else {
                navigateToHome()
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            getLastLocation()
        } else {
            showLocationErrorDialog()
        }
    }

    private fun startWeatherNotificationAlarm() {
        // Get the AlarmManager service
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        // Create an Intent for the WeatherNotificationReceiver to handle the notification
        val intent = Intent(this, WeatherNotificationReceiver::class.java)

        // Create a PendingIntent that will trigger the broadcast receiver
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Set the alarm to trigger the notification every minute
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + 60000, // Delay the first notification by one minute
            60000, // Repeat every minute
            pendingIntent
        )
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    CurrentLocation.saveLocation(it.latitude, it.longitude)
                } ?: run {
                    // Handle the case where location is null
                    showLocationErrorDialog()
                }
            }
        }
    }

    private fun showLocationErrorDialog() {
        AlertDialog.Builder(this)
            .setTitle("Location Error")
            .setMessage("Cannot get location. Please enable location services and try again.")
            .setPositiveButton("Retry") { _, _ -> getLastLocation()
                recreate()
            }
            .setNegativeButton("Cancel") { _, _ -> finish() }
            .show()
    }

    private fun navigateToHome() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}