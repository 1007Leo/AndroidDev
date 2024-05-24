package com.mmcs.todolist.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mmcs.todolist.viewmodel.ToDoListViewModel

class ViewModelFactory(private val dao: TaskDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToDoListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ToDoListViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}