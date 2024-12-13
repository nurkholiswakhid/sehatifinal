package com.example.sehati.room.recommend

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecommedDao {
    @Insert
    suspend fun insertRC(recommend: Recommend)

    @Query("SELECT * FROM recommend")
    fun getAllRC(): LiveData<List<Recommend>>
}