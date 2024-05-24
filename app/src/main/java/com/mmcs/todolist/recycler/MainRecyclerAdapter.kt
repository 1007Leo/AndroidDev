package com.mmcs.todolist.recycler

import android.graphics.Paint
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
        val showInfoListener = largeTextView.setOnClickListener {
            data.adapterPosition = adapterPosition
            val navController = itemView.findNavController()
            navController.navigate(R.id.nodeInfoFragment)
        }
        val checkboxListener = completedCheckBox.setOnClickListener {
            if (completedCheckBox.isChecked) {
                data.setCompletedState(adapterPosition, true)
                largeTextView.apply {
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }
            }
            else {
                data.setCompletedState(adapterPosition, false)
                largeTextView.apply {
                    paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MainViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.getTasksCount()
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.completedCheckBox.isChecked = data.getTask(position).completed
        if (holder.completedCheckBox.isChecked)
            holder.largeTextView.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
        else
            holder.largeTextView.apply {
                paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }

        holder.largeTextView.text = data.getTask(position).name
    }
}