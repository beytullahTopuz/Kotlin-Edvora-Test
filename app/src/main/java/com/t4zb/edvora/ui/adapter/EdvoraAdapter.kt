package com.t4zb.edvora.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.t4zb.edvora.R
import com.t4zb.edvora.helper.PicassoHelper
import com.t4zb.edvora.modelLayer.rest.service.event.EdvoraModel
import com.t4zb.edvora.ui.viewmodel.SharedViewModel

class EdvoraAdapter(context: Context, edvoraList: List<EdvoraModel>, viewModel: SharedViewModel) :
    RecyclerView.Adapter<EdvoraAdapter.ViewHolder>() {

    private var mContext = context
    private var mList = edvoraList
    private var mViewModel = viewModel


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var map_img: ImageView = itemView.findViewById(R.id.map_img)
        var state_text: TextView = itemView.findViewById(R.id.state_text)
        var city_text: TextView = itemView.findViewById(R.id.city_text)
        var ride_id_text: TextView = itemView.findViewById(R.id.ride_id_text)
        var origin_station_text: TextView = itemView.findViewById(R.id.origin_station_text)
        var station_path_text: TextView = itemView.findViewById(R.id.station_path_text)
        var date_text: TextView = itemView.findViewById(R.id.date_text)
        var distance_text: TextView = itemView.findViewById(R.id.distance_text)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EdvoraAdapter.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.item_map_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EdvoraAdapter.ViewHolder, position: Int) {

        var currentPosition = mList[position]
        PicassoHelper.picassoOkhttp(mContext, currentPosition.map_url, holder.map_img)

        holder.city_text.text = currentPosition.city
        holder.state_text.text = currentPosition.state
        holder.ride_id_text.text = "Ride Id: ${currentPosition.id}"

        holder.origin_station_text.text = "Origin Station: ${currentPosition.origin_station_code}"

        holder.station_path_text.text = "station_path: ${currentPosition.station_path}"
        holder.date_text.text = "Date: ${currentPosition.date}"
        holder.distance_text.text = "Distance: ${currentPosition.destination_station_code}"

      /*  with(holder) {

            map_img.let {
                PicassoHelper.picassoUtils(mContext, mList[position].map_url, map_img)
            }

        }
        */
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}