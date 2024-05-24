package com.mmcs.todolist.database

import android.app.Application
import androidx.room.Room

class GlobalDBApplication: Application()  {
    private var db: TaskDatabase? = null

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "tasks.db"
        ).allowMainThreadQueries().build()
    }

    fun getDB(): TaskDatabase {
        return db!!
    }
}