package com.mmcs.todolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TaskModel (
    var name: String,
    var description: String,
    var completed: Boolean
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}