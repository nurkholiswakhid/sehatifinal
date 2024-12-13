package com.example.sehati.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sehati.ResultState
import com.example.sehati.api.ApiConfig
import com.example.sehati.api.body.DiagnoseBody
import com.example.sehati.api.body.RegisterBody
import com.example.sehati.api.response.DefaultResponse
import com.example.sehati.api.response.DiagnoseResponse
import com.example.sehati.api.response.SymptomsResponse
import com.example.sehati.room.AppDatabase
import com.example.sehati.room.diagnose.Diagnose
import kotlinx.coroutines.launch

class HealthViewModel(application: Application) : AndroidViewModel(application) {
    fun symptoms(): LiveData<ResultState<SymptomsResponse>> {
        val state = MutableLiveData<ResultState<SymptomsResponse>>()
        state.value = ResultState.Loading()
        viewModelScope.launch {
            try {
                val symptoms = ApiConfig.getBaseApiService()?.symptoms()
                if (symptoms?.isSuccessful == true) {
                    state.value = ResultState.Success(symptoms.body()!!)
                } else {
                    state.value = ResultState.Error("Failed")
                }
            } catch (e: Exception) {
                state.value = ResultState.Error(e.message)
            }
        }
        return state
    }

    fun diagnose(body: DiagnoseBody): LiveData<ResultState<DiagnoseResponse>> {
        val state = MutableLiveData<ResultState<DiagnoseResponse>>()
        state.value = ResultState.Loading()
        viewModelScope.launch {
            try {
                val diagnose = ApiConfig.getBaseApiService()?.diagnose(body)
                if (diagnose?.isSuccessful == true) {
                    state.value = ResultState.Success(diagnose.body()!!)
                } else {
                    state.value = ResultState.Error("Failed")
                }
            } catch (e: Exception) {
                state.value = ResultState.Error(e.message)
            }
        }
        return state
    }

    fun insertDiagnose(diagnose: Diagnose) {
        viewModelScope.launch {
            AppDatabase.getInstance(getApplication()).diagnoseDao().insertDiagnose(diagnose)
        }
    }
}