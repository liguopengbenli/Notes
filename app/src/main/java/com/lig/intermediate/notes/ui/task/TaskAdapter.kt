package com.lig.intermediate.notes.ui.task

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.foundations.BaseRecycleAdapter
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.views.TodoView
import kotlinx.android.synthetic.main.item_task.view.*
import kotlinx.android.synthetic.main.view_todo.view.*

class TaskAdapter(
    taskList: MutableList<Task> = mutableListOf()
): BaseRecycleAdapter<Task>(taskList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))

    class ViewHolder(view: View): BaseViewHolder<Task>(view){
        override fun onBind(data: Task) {
            view.item_task_title.text = data.title
            // for each tod we inflate a view and populate and attach to parent container
            data.todos.forEach { todo ->
                // cast to new view
             val todoView =   (LayoutInflater.from(view.context).inflate(R.layout.view_todo, view.todoContainer, false) as TodoView ).apply {
                 initView(todo)
             }
                // R.layout.view_todo : to get resources from here
                // view.todoContainer: parent view group
                view.todoContainer.addView(todoView) // attach to parent
            }
        }

    }


}