package com.example.sehati.room.diagnose

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Diagnose(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val date: Int,
    val diagnose: String,
    val symptoms: String,
)