package com.mmcs.todolist.viewmodel

interface ToDoList<T> {
    //val Tasks: MutableList<T>

    fun addTask(data: T)

    fun removeTask(ind: Int)

    fun setCompletedState(ind: Int, state: Boolean)

    fun getTask(ind: Int): T

    fun getAllTasks(): List<T>
}