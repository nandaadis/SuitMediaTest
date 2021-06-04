package com.example.suitmediatest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.suitmediatest.data.model.Data
import com.example.suitmediatest.data.model.GuestModel
import com.example.suitmediatest.databinding.ItemGuestBinding
import com.google.android.material.snackbar.Snackbar

class GuestAdapter(val listItem: List<Data>, val activity: FragmentActivity, val view: Fragment): RecyclerView.Adapter<GuestAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemGuestBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var name = "${listItem[position].first_name} ${listItem[position].last_name}"

        Glide
            .with(holder.itemView.context)
            .load(listItem[position].avatar)
            .circleCrop()
            .into(holder.binding.ivPhoto)

        holder.binding.tvName.setText(name)

        holder.binding.view.setOnClickListener {
            NavHostFragment.findNavController(view).previousBackStackEntry?.savedStateHandle?.set(
                "key_guest",
                name
            )


            Toast.makeText(
                activity,
                checkCategory(listItem[position].id),
                LENGTH_SHORT
            ).show()

            activity.onBackPressed()

        }
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    fun checkCategory(id: Int): String{
        if(id%2 == 0 && id%3 == 0){
            return "iOS"
        } else if (id%2 == 0) {
            return "blackberry"
        } else if (id%3 == 0) {
            return "android"
        } else
            return "feature phone"

    }


}