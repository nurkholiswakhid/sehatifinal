package com.example.sehati.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sehati.room.AppDatabase

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    fun getAllRC() = AppDatabase.getInstance(getApplication()).recommendDao().getAllRC()
    fun getSingleDiagnose() = AppDatabase.getInstance(getApplication()).diagnoseDao().getSingleDiagnose()
}