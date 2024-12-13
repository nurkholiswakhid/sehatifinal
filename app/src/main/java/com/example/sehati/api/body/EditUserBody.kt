package com.example.sehati.api.body

data class EditUserBody(
    val data: RegisterData
) {
    data class RegisterData(
        val name: String,
        val age:String,
        val email: String,
    )
}
