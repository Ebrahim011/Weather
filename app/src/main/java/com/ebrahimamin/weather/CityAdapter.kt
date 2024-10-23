// app/src/main/java/com/ebrahimamin/weather/CityAdapter.kt
package com.ebrahimamin.weather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ebrahimamin.weather.api_files.Location
import com.ebrahimamin.weather.api_files.WeatherResponse
import com.ebrahimamin.weather.api_files.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityAdapter(private var cities: MutableList<Location>, private val onItemClick: (Location) -> Unit) :
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private val loadedItems = mutableSetOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_recycler, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int = cities.size

    override fun onViewAttachedToWindow(holder: CityViewHolder) {
        super.onViewAttachedToWindow(holder)
        val position = holder.adapterPosition
        if (!loadedItems.contains(position)) {
            holder.loadWeatherData()
        }
    }



    private fun removeItem(position: Int) {
        cities.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityNameTextView: TextView = itemView.findViewById(R.id.tv_city_recycler)
        private val temperatureTextView: TextView = itemView.findViewById(R.id.tv_temperature)
        private val weatherIconImageView: ImageView = itemView.findViewById(R.id.iv_weather_condition)

        init {
            itemView.setOnClickListener {
                val city = cities[adapterPosition]
                onItemClick(city)
            }
        }

        fun bind(city: Location) {
            cityNameTextView.text = city.cityName
        }

        fun loadWeatherData() {
            val city = cities[adapterPosition]
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = RetrofitInstance.weatherApi.getWeather(city.location, "1")
                    if (response.isSuccessful) {
                        val weatherResponse: WeatherResponse? = response.body()
                        withContext(Dispatchers.Main) {
                            weatherResponse?.let {
                                updateUI(it)
                                loadedItems.add(adapterPosition)
                            }
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            removeItem(adapterPosition)
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        removeItem(adapterPosition)
                    }
                }
            }
        }

        @SuppressLint("SetTextI18n")
        private fun updateUI(weatherResponse: WeatherResponse) {
            temperatureTextView.text = "${weatherResponse.current.temp_c}°C"
            Glide.with(itemView.context)
                .load("https:${weatherResponse.current.condition.icon}")
                .into(weatherIconImageView)
        }
    }
}