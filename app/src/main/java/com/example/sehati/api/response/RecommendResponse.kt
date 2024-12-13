package com.example.sehati.api.response

data class RecommendResponse(
    val success:Boolean,
    val message: String,
    val data: DiagnoseData
) {
    data class DiagnoseData(
        val recommendation:String,
    )
}
