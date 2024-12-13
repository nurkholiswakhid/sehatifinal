package com.example.sehati.room.recommend

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recommend(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val recommendation: String,
)