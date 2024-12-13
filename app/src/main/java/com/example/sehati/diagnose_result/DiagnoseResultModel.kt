package com.example.sehati.diagnose_result

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
import com.example.sehati.api.response.RecommendResponse
import com.example.sehati.api.response.SymptomsResponse
import com.example.sehati.room.AppDatabase
import com.example.sehati.room.recommend.Recommend
import kotlinx.coroutines.launch

class DiagnoseResultModel(application: Application) : AndroidViewModel(application) {

    fun recommend(body: DiagnoseBody): LiveData<ResultState<RecommendResponse>> {
        val state = MutableLiveData<ResultState<RecommendResponse>>()
        state.value = ResultState.Loading()
        viewModelScope.launch {
            try {
                val diagnose = ApiConfig.getBaseApiService()?.recommend(body)
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

    fun insertRecommend(recommend: Recommend) = viewModelScope.launch {
        AppDatabase.getInstance(getApplication()).recommendDao().insertRC(recommend)
    }
}