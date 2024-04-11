package com.mmcs.todolist.viewmodel

import androidx.lifecycle.ViewModel

data class Node(val name: String, val description: String, var completed: Boolean)

class ToDoListViewModel: ToDoList<Node>, ViewModel() {
    override val Tasks = mutableListOf<Node>()
    var adapterPosition = -1

    override fun addTask(data: Node) {
        Tasks.add(0, data)
    }

    override fun removeTask(ind: Int) {
        Tasks.removeAt(ind)
    }

    override fun setCompletedState(ind: Int, state: Boolean) {
        Tasks.elementAt(ind).completed = state
        //Tasks.elementAt(ind).apply { completed = !completed }
    }

    override fun getTask(ind: Int): Node {
        return Tasks.elementAt(ind)
    }

    override fun getAllTasks(): List<Node> {
        return Tasks
    }
}