package com.example.sehati.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.sehati.BottomNavActivity
import com.example.sehati.DetailRekomendasiActivity
import com.example.sehati.databinding.ItemRekomendasiBinding
import com.example.sehati.room.recommend.Recommend

class RecommendAdapter(private val list: List<Recommend>) : RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendAdapter.ViewHolder {
        val binding = ItemRekomendasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendAdapter.ViewHolder, position: Int) {
       val p = list[position]

        holder.binding.recTxt.text = p.recommendation
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: ItemRekomendasiBinding) : RecyclerView.ViewHolder(binding.root)
}