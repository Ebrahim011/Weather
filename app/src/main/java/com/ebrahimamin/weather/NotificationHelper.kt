package com.ebrahimamin.weather

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper(private val context: Context) {

    // Define the channel ID and name for notifications
    private val channelId = "weather_channel_id"
    private val channelName = "Weather Notifications"

    // Function to create a notification channel (required for Android O and above)
    fun createNotificationChannel() {
        // Check if the Android version is Oreo (API level 26) or above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a notification channel with the specified ID and name
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            // Get the NotificationManager system service
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            // Create the notification channel
            notificationManager.createNotificationChannel(channel)
        }
    }

    // Function to show the notification with city name and temperature
    fun showNotification(cityName: String, temperature: String) {
        // Create the notification channel if it doesn't exist
        createNotificationChannel()

        // Create an Intent to launch the WeatherFragment when the notification is tapped
        val intent = Intent(context, WeatherFragment::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        // Create a PendingIntent for the notification click action
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // Build the notification using NotificationCompat.Builder
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_weather) // Set the small icon for the notification
            .setContentTitle("Current Weather in $cityName") // Set the notification title
            .setContentText("Temperature: $temperature°C") // Set the notification text
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) // Set the notification priority
            .setContentIntent(pendingIntent) // Set the PendingIntent for notification click
            .setAutoCancel(true) // Automatically remove the notification when tapped

        // Get the NotificationManager system service
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Notify the user with the built notification
        notificationManager.notify(1, notificationBuilder.build()) // Notify with ID 1
    }
}
