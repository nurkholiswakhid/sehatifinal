package com.example.sehati.ui.history

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.sehati.BottomNavActivity
import com.example.sehati.DetailRekomendasiActivity
import com.example.sehati.databinding.ItemRekomendasiBinding
import com.example.sehati.databinding.ItemRiwayatBinding
import com.example.sehati.room.diagnose.Diagnose
import java.text.SimpleDateFormat
import java.util.Date

class HistoryAdapter(val list: List<Diagnose>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val binding = ItemRiwayatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
       val p = list[position]

        with(holder) {
            binding.diagnoseTxt.text = p.diagnose
            binding.symptomsTxt.text = p.symptoms
            binding.dateTxt.text = convert(p.date.toLong())
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: ItemRiwayatBinding) : RecyclerView.ViewHolder(binding.root)

    fun convert(long: Long):String {
        val dateFormat = SimpleDateFormat("dd-mm-yyyy");

        return dateFormat.format(Date(long))
    }
}