package com.example.sehati.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sehati.room.diagnose.Diagnose
import com.example.sehati.room.diagnose.DiagnoseDao
import com.example.sehati.room.recommend.RecommedDao
import com.example.sehati.room.recommend.Recommend

@Database(entities = [Diagnose::class, Recommend::class], exportSchema = false, version = 1)
abstract class AppDatabase :RoomDatabase() {

    abstract fun diagnoseDao(): DiagnoseDao
    abstract fun recommendDao(): RecommedDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "app"
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }



}