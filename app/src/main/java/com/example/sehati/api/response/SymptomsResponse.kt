package com.example.sehati.api.response

data class SymptomsResponse(
    val success:Boolean,
    val message: String,
    val data: List<String>
)
