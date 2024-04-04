package com.example.prandriod

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GunAdapter(private val guns: List<Gun>) : RecyclerView.Adapter<GunAdapter.GunViewHolder>() {

    inner class GunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GunViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_guns_adapter, parent, false)
        return GunViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GunViewHolder, position: Int) {
        val gun = guns[position]
        holder.nameTextView.text = gun.name
        holder.descriptionTextView.text = gun.description
        holder.priceTextView.text = "Price: $${gun.price}"
    }

    override fun getItemCount(): Int {
        return guns.size
    }
}

