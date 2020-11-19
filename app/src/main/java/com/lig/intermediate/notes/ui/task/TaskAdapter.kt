package com.lig.intermediate.notes.ui.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.models.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(
    private val taskList: MutableList<Task> = mutableListOf()

): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // cast holder to our custom viewholder
        (holder as ViewHolder).onBind(taskList[position])
    }

    override fun getItemCount(): Int = taskList.size

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun onBind(task: Task){
            view.item_task_text.text = task.title
        }
    }


}