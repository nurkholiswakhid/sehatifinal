package com.example.sehati.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.example.sehati.ResultState
import com.example.sehati.api.ApiConfig
import com.example.sehati.api.body.LoginBody
import com.example.sehati.api.response.DefaultResponse
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application){
    fun login(body: LoginBody): LiveData<ResultState<DefaultResponse>> {
        val state = MutableLiveData<ResultState<DefaultResponse>>()
        state.value = ResultState.Loading()
        viewModelScope.launch {
            try {
                val login = ApiConfig.getBaseApiService()?.login(body)
                if (login?.isSuccessful == true) {
                    state.value = ResultState.Success(login.body()!!)
                } else {
                    state.value = ResultState.Error("Failed")
                }
            } catch (e: Exception) {
                state.value = ResultState.Error(e.message)
            }
        }
        return state
    }
}