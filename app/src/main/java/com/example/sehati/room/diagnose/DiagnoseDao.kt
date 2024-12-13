package com.example.sehati.room.diagnose

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiagnoseDao {
    @Insert
    suspend fun insertDiagnose(diagnose: Diagnose)

    @Query("SELECT * FROM diagnose")
    fun getAllDiagnose(): LiveData<List<Diagnose>>

    @Query("SELECT * FROM diagnose ORDER BY date DESC LIMIT 1")
    fun getSingleDiagnose(): LiveData<List<Diagnose>>
}