package com.example.prandriod

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prandriod.Caliber

class CaliberAdapter(private val caliberList: List<Caliber>) : RecyclerView.Adapter<CaliberAdapter.CaliberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaliberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_caliber_adapter, parent, false)
        return CaliberViewHolder(view)
    }

    override fun onBindViewHolder(holder: CaliberViewHolder, position: Int) {
        val caliber = caliberList[position]
        holder.bind(caliber)
    }

    override fun getItemCount() = caliberList.size

    inner class CaliberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val caliberNameTextView: TextView = itemView.findViewById(R.id.caliberNameTextView)
        private val caliberPriceTextView: TextView = itemView.findViewById(R.id.caliberPriceTextView)

        @SuppressLint("SetTextI18n")
        fun bind(caliber: Caliber) {
            caliberNameTextView.text = caliber.name
            caliberPriceTextView.text = "Price: $${caliber.price}"
        }
    }
}
