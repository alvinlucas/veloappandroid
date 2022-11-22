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
import com.example.velonantes.model.Park
import com.example.velonantes.model.currentLocation
import com.example.velonantes.model.parkSelected
import com.example.velonantes.ui.park.ParkDetailActivity

class ParkAdapter(private val parks:List<Park>, private val context: Context ) :
    RecyclerView.Adapter<ParkAdapter.ViewHolder>(){

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val cardView : CardView = itemView.findViewById(R.id.cardView)
    val name : TextView = itemView.findViewById(R.id.name)
    val disponibilite : TextView = itemView.findViewById(R.id.disponibilite)
    val distance : TextView = itemView.findViewById(R.id.distance)
    val status : ImageView = itemView.findViewById(R.id.status)


}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_parking_item,parent,false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val park = parks[position]
        holder.name.text = park.grpNom
        if (currentLocation != null){
            holder.distance.text = "${String.format("%.2f",currentLocation!!.distanceTo(park.toLocation())/1000)}KM"
        }else{
            holder.distance.text = " - KM"
        }
        holder.disponibilite.text = park.grpDisponible.toString() + " places"
        if(park.grpDisponible != 0){
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_checked_24)
        }else{
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
        }
        holder.cardView.setOnClickListener{
            val intent = Intent(context, ParkDetailActivity::class.java)
            parkSelected = park

            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return parks.size
    }
    }


