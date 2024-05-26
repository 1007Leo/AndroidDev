package com.mmcs.memeapiclient.database

import android.app.Application
import androidx.room.Room

class GlobalDbApplication: Application() {
    private var db: ImageDatabase? = null

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            ImageDatabase::class.java,
            "tasks.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    fun getDB(): ImageDatabase {
        return db!!
    }
}