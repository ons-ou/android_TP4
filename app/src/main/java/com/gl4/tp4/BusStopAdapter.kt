package com.gl4.tp4

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.entities.Schedule
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class BusStopAdapter(private var busStops: List<Schedule>, private val onItemClick: (Schedule) -> Unit) :
    RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stopText: TextView
        val arrivalText: TextView

        init {
            stopText = itemView.findViewById(R.id.textStopName)
            arrivalText = itemView.findViewById(R.id.textArrival)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: BusStopAdapter.ViewHolder, position: Int) {
        val stop = busStops[position]

        holder.stopText.text = stop.stopName
        val instant = Instant.ofEpochSecond(stop.arrivalTime).minus(Duration.ofHours(7))

        val zoneId = ZoneId.systemDefault()

        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val formattedTime = instant.atZone(zoneId).format(formatter)
        holder.arrivalText.text = formattedTime
        holder.itemView.setOnClickListener {
            onItemClick(stop)
        }
    }


    override fun getItemCount(): Int {
        return busStops.size

    }

    fun updateList(list: List<Schedule>){
        busStops = list
        notifyDataSetChanged()

    }
}
