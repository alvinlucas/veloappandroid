package com.example.velonantes.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.velonantes.R
import com.example.velonantes.model.Station
import com.example.velonantes.model.currentLocation
import com.example.velonantes.model.stationSelected
import com.example.velonantes.ui.station.StationDetailActivity
import com.example.velonantes.ui.station.StationMapsActivity

class StationAdapter(private val stations:List<Station>, private val context: Context ) :
    RecyclerView.Adapter<StationAdapter.ViewHolder>(){

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val cardView : CardView = itemView.findViewById(R.id.cardView)
    val name : TextView = itemView.findViewById(R.id.name)
    val adress : TextView = itemView.findViewById(R.id.adress)
    val status : ImageView = itemView.findViewById(R.id.status)
    val availability : TextView = itemView.findViewById(R.id.availability)
    val distance : TextView = itemView.findViewById(R.id.distance)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_station_item,parent,false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = stations[position]
        holder.name.text = station.name
        if (currentLocation != null){
            holder.distance.text = "${String.format("%.2f",currentLocation!!.distanceTo(station.toLocation())/1000)}KM"
        }else{
            holder.distance.text = " - KM"
        }
        holder.adress.text = station.address
        holder.availability.text = "🚲${station.availableBikes} 📣${station.availableBikes} ✅${station.bikeStands}"
        holder.availability.text = station.showDetails()
        if("OPEN" == station.status){
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_checked_24)
        }else{
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
        }
        if( 0 == station.availableBikes.toInt()){
            holder.name.setTextColor(context.getColor(R.color.empty_bike))
        }else{
            holder.name.setTextColor(context.getColor(R.color.black))
        }

        holder.cardView.setOnClickListener{
            val intent = Intent(context, StationDetailActivity::class.java)
            stationSelected = station

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return stations.size
    }
}