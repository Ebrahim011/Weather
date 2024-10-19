package com.ebrahimamin.weather

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

// This class is responsible for creating and displaying a weather notification
class WeatherNotificationReceiver : BroadcastReceiver() {

    // This method is triggered when the BroadcastReceiver receives an intent (broadcast)
    override fun onReceive(context: Context, intent: Intent) {
        // Get the NotificationManager system service to manage notifications
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notification ID to uniquely identify this notification
        val notificationId = 1

        // Define the notification channel ID for Android versions 8.0 and above
        val channelId = "weather_channel"

        // If the Android version is Oreo (8.0) or above, create a notification channel
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Weather Notifications", NotificationManager.IMPORTANCE_DEFAULT
            )
            // Register the notification channel with the system
            notificationManager.createNotificationChannel(channel)
        }

        // Create the notification with title, content, icon, and auto-cancel behavior
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Weather Update") // Title of the notification
            .setContentText("Your weather update is ready!") // Placeholder for actual weather data
            .setSmallIcon(R.drawable.ic_weather) // Small icon for the notification (ensure icon exists)
            .setAutoCancel(true) // Automatically remove notification when tapped

        // Trigger the notification to display using the NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}
