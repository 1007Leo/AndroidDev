package com.mmcs.todolist.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mmcs.todolist.R
import com.mmcs.todolist.viewmodel.ToDoListViewModel

class MainRecyclerAdapter(private val data: ToDoListViewModel):
    RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> ()
{
    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val completedCheckBox: CheckBox = itemView.findViewById(R.id.completedCheckBox)
        val largeTextView: TextView = itemView.findViewById(R.id.nodeName)
        val removeListener = itemView.findViewById<Button>(R.id.delete).setOnClickListener{
            data.removeTask(adapterPosition)
            notifyItemRemoved(adapterPosition)
        }
        val showInfoListener = itemView.findViewById<TextView>(R.id.nodeName).setOnClickListener {
            val navController = itemView.findNavController()
            data.adapterPosition = adapterPosition
            navController.navigate(R.id.nodeInfoFragment)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MainViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.getAllTasks().size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (holder.completedCheckBox.isChecked)
            data.setCompletedState(position, true)
        else
            data.setCompletedState(position, false)

        holder.largeTextView.text = data.getTask(position).name
    }
}