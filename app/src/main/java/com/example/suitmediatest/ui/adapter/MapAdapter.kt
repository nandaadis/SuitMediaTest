package com.example.suitmediatest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.suitmediatest.R
import com.example.suitmediatest.data.model.Data
import com.example.suitmediatest.data.model.EventModel
import com.example.suitmediatest.databinding.ItemEventBinding
import com.example.suitmediatest.databinding.ItemGuestBinding
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class MapAdapter(val listItem: MutableList<EventModel>, val activity: FragmentActivity, val view: Fragment, val GMap : GoogleMap) :
    RecyclerView.Adapter<MapAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide
            .with(holder.itemView.context)
            .load(listItem[position].img)
            .transform(
                MultiTransformation(CenterCrop(), RoundedCornersTransformation(30,0, RoundedCornersTransformation.CornerType.TOP_LEFT), RoundedCornersTransformation(30,0, RoundedCornersTransformation.CornerType.BOTTOM_LEFT))
            )
            .into(holder.binding.ivPhoto)


        holder.binding.tvName.setText(listItem[position].name)
        holder.binding.tvDate.setText(listItem[position].date)

        holder.binding.view.setOnClickListener {
            GMap.setOnMarkerClickListener { marker ->
                marker.setIcon(
                    BitmapDescriptorFactory.fromResource(
                    R.drawable.ic_marker_selected))
                true
            }

            GMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(listItem[position].lat, listItem[position].long)))
        }

    }

    override fun getItemCount(): Int {
        return listItem.size
    }



}