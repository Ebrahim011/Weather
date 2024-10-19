// app/src/main/java/com/ebrahimamin/weather/HourlyAdapter.kt
package com.ebrahimamin.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ebrahimamin.weather.api_files.Hour

class HourlyAdapter(private val hourlyData: List<Hour>) :
    RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hour_recycler, parent, false)
        return HourlyViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        val hour = hourlyData[position]
        holder.bind(hour)
    }

    override fun getItemCount(): Int = hourlyData.size

    inner class HourlyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val hourTextView: TextView = itemView.findViewById(R.id.hourTV)
        private val temperatureTextView: TextView = itemView.findViewById(R.id.temperatureTV)
        private val conditionImageView: ImageView = itemView.findViewById(R.id.conditionIV)

        fun bind(hour: Hour) {
            hourTextView.text = hour.time.substring(11, 16) // Extracting the time part
            temperatureTextView.text = "${hour.temp_c}°"
            Glide.with(itemView.context)
                .load("https:${hour.condition.icon}")
                .into(conditionImageView)
        }
    }
}