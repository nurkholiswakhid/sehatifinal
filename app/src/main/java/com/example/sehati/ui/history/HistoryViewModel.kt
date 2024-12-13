package com.example.sehati.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.sehati.room.AppDatabase
import com.example.sehati.room.diagnose.Diagnose

class HistoryViewModel(application: Application):AndroidViewModel(application) {
    fun getAll():LiveData<List<Diagnose>> {
        return  AppDatabase.getInstance(getApplication()).diagnoseDao().getAllDiagnose()
    }
}