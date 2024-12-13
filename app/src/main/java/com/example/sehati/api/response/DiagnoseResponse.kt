package com.example.sehati.api.response

data class DiagnoseResponse(
    val success:Boolean,
    val message: String,
    val data: DiagnoseData
) {
    data class DiagnoseData(
        val diagnosis:String,
        val confidence:String
    )
}
