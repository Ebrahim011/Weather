import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ebrahimamin.weather.R
import com.ebrahimamin.weather.api_files.Forecastday
import java.text.SimpleDateFormat
import java.util.Locale

class DailyAdapter(private val forecastList: List<Forecastday>) : RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.daily_recycler, parent, false)
        return DailyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val forecastDay = forecastList[position]
        holder.bind(forecastDay)
    }

    override fun getItemCount(): Int {
        return forecastList.take(5).size
    }

    class DailyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayInWeekTV: TextView = itemView.findViewById(R.id.dayinweekTV)
        private val dateTV: TextView = itemView.findViewById(R.id.dateTV)
        private val dayConditionIV: ImageView = itemView.findViewById(R.id.dayConidiionIV)
        private val maxAndMinDayTemperatureTV: TextView = itemView.findViewById(R.id.maxandmin_dayTemperatureTV)

        @SuppressLint("SetTextI18n")
        fun bind(forecastDay: Forecastday) {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(forecastDay.date)
            val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(date)
            val monthDay = SimpleDateFormat("MMM dd", Locale.getDefault()).format(date)

            dayInWeekTV.text = dayOfWeek
            dateTV.text = monthDay
            maxAndMinDayTemperatureTV.text = "${forecastDay.day.maxtemp_c}°/${forecastDay.day.mintemp_c}°"
            Glide.with(itemView.context)
                .load("https:${forecastDay.day.condition.icon}")
                .into(dayConditionIV)

            // Log the data to check if it's being bound correctly
            Log.d("DailyAdapter", "Binding data: $dayOfWeek, $monthDay, ${forecastDay.day.maxtemp_c}°/${forecastDay.day.mintemp_c}°")
        }
    }
}