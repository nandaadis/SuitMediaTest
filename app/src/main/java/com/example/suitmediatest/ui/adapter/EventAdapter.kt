package com.example.suitmediatest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.suitmediatest.data.model.EventModel
import com.example.suitmediatest.data.model.GuestModel
import com.example.suitmediatest.databinding.ItemEventBinding
import com.example.suitmediatest.databinding.ItemGuestBinding
import kotlinx.android.synthetic.main.item_event.*

class EventAdapter(val listItem: MutableList<EventModel>, val activity: FragmentActivity, val view: Fragment) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {
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
                MultiTransformation(CenterCrop(), RoundedCorners(20))
            )
            .into(holder.binding.ivPhoto)


        holder.binding.tvName.setText(listItem[position].name)
        holder.binding.tvDate.setText(listItem[position].date)

        holder.binding.view.setOnClickListener {
            findNavController(view).previousBackStackEntry?.savedStateHandle?.set(
                "key_event",
                listItem[position].name
            )
            activity?.onBackPressed()
        }

    }

    override fun getItemCount(): Int {
        return listItem.size
    }


}