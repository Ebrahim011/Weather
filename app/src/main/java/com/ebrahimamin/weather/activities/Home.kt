// Home.kt
package com.ebrahimamin.weather.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.ebrahimamin.weather.CurrentLocation
import com.ebrahimamin.weather.R

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        // Initialize CurrentLocation
        CurrentLocation.init(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        navHostFragment?.navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.WeatherFragment -> {
                    // Handle destination change
                }
                R.id.searchFragment -> {
                    // Set the title to "Search"
                    supportActionBar?.title = "Search"
                }
            }
        }
    }
}