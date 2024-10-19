package com.ebrahimamin.weather

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ebrahimamin.weather.activities.MainActivity

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Check if the received broadcast is for the system boot completion event
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            // Create an intent to launch the MainActivity after device boot is completed
            val mainIntent = Intent(context, MainActivity::class.java)

            // Start the MainActivity (This will make the app open automatically after device boot)
            context.startActivity(mainIntent)
        }
    }
}

