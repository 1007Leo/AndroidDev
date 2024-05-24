package com.mmcs.todolist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert
    fun insertTask(task: TaskModel)

    @Delete
    fun deleteTask(task: TaskModel)

    @Update
    fun updateTask(task: TaskModel)

    @Query("SELECT * FROM taskmodel WHERE id=:id")
    suspend fun getTask(id: Int): TaskModel
    @Query("SELECT * FROM taskmodel")
    fun getAllTasks(): List<TaskModel>

    @Query("SELECT COUNT(id) FROM taskmodel")
    fun getTasksCount(): Int
}