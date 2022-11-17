package ru.pavelapk.weatherapp.data.db

import android.content.Context
import androidx.room.Room

object Database {
    private const val DATABASE_NAME = "app_database"

    fun build(context: Context): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
        .build()
}
