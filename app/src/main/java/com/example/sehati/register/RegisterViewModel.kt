package com.example.sehati.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sehati.ResultState
import com.example.sehati.api.ApiConfig
import com.example.sehati.api.body.RegisterBody
import com.example.sehati.api.response.DefaultResponse
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    fun register(body: RegisterBody): LiveData<ResultState<DefaultResponse>> {
        val state = MutableLiveData<ResultState<DefaultResponse>>()
        state.value = ResultState.Loading()
        viewModelScope.launch {
            try {
                val register = ApiConfig.getBaseApiService()?.register(body)
                if (register?.isSuccessful == true) {
                    state.value = ResultState.Success(register.body()!!)
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