package com.mmcs.todolist.viewmodel

import androidx.lifecycle.ViewModel
import com.mmcs.todolist.database.TaskDao
import com.mmcs.todolist.database.TaskModel

class ToDoListViewModel(private val dao: TaskDao): ToDoList<TaskModel>, ViewModel() {
    var adapterPosition = -1

    override fun addTask(data: TaskModel) {
        dao.insertTask(data)
    }

    override fun removeTask(ind: Int) {
        dao.deleteTask(getTask(ind))
    }

    override fun setCompletedState(ind: Int, state: Boolean) {
        val task = getTask(ind)
        task.completed = state
        dao.updateTask(task)
    }

    override fun getTask(ind: Int): TaskModel {
        return dao.getAllTasks().elementAt(ind)
    }

    override fun getAllTasks(): List<TaskModel> {
        return dao.getAllTasks()
    }

    fun getTasksCount(): Int {
        return dao.getTasksCount()
    }
}