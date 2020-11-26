package com.lig.intermediate.notes.ui.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.foundations.BaseRecycleAdapter
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.navigation.NavigationActivity.Companion.FRAGEMENT_VALUE_TASK
import com.lig.intermediate.notes.views.TaskView
import kotlinx.android.synthetic.main.view_add_button.view.*


class TaskAdapter(
    taskList: MutableList<Task> = mutableListOf(),
    val touchActionDelegate: TaskFragment.TouchActionDelegate,
    val dataActionDelegate: TaskListViewContract
): BaseRecycleAdapter<Task>(taskList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = if(viewType == TYPE_INFO){
        TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
    } else {
        AddButtonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_add_button, parent, false))
    }

    inner class TaskViewHolder(view: View): BaseViewHolder<Task>(view){
        override fun onBind(data: Task, listIndex: Int) {
            (view as TaskView).initView(data){ todoIndex, isChecked ->
                dataActionDelegate.onTodoUpdated(listIndex, todoIndex, isChecked)
            }
        }
    }
    // inner class to use the member variable
    inner class AddButtonViewHolder(view: View): BaseRecycleAdapter.AddButtonViewHolder(view) {
        override fun onBind(data: Unit, listeIndex: Int) {
            view.buttonText.text = view.context.getString(R.string.add_button_task)

            view.setOnClickListener {
                touchActionDelegate.onAddButtonClicked(FRAGEMENT_VALUE_TASK)
            }
        }
    }


}