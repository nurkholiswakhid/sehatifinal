package com.example.sehati.api

import com.example.sehati.api.body.DiagnoseBody
import com.example.sehati.api.body.EditUserBody
import com.example.sehati.api.body.LoginBody
import com.example.sehati.api.body.RegisterBody
import com.example.sehati.api.response.DefaultResponse
import com.example.sehati.api.response.DiagnoseResponse
import com.example.sehati.api.response.RecommendResponse
import com.example.sehati.api.response.SymptomsResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("auth/login")
    suspend fun login(
        @Body body:LoginBody
    ): Response<DefaultResponse>

    @POST("auth/register")
    suspend fun register(
        @Body body:RegisterBody
    ): Response<DefaultResponse>

    @PUT("user/edit")
    suspend fun editUser(
        @Body body:EditUserBody
    ): Response<DefaultResponse>

    @GET("health/symptoms")
    suspend fun symptoms(
    ): Response<SymptomsResponse>

    @POST("health/diagnose")
    suspend fun diagnose(
        @Body body:DiagnoseBody
    ): Response<DiagnoseResponse>

    @POST("health/recommendations")
    suspend fun recommend(
        @Body body:DiagnoseBody
    ): Response<RecommendResponse>

}