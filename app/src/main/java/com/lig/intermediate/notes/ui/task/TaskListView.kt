package com.lig.intermediate.notes.ui.task

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.lig.intermediate.notes.models.Task
import kotlinx.android.synthetic.main.fragment_task.view.*

class TaskListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var adapter: TaskAdapter
    private lateinit var touchActionDelegate: TaskFragment.TouchActionDelegate
    private lateinit var dataActionDelegate: TaskListViewContract

    fun initView(taDelegate: TaskFragment.TouchActionDelegate, daDelegate: TaskListViewContract) {
        setDelegate(taDelegate, daDelegate)
        setUpView()
    }

    private fun setDelegate(
        taDelegate: TaskFragment.TouchActionDelegate,
        daDelegate: TaskListViewContract
    ) {
        touchActionDelegate = taDelegate
        dataActionDelegate = daDelegate
    }

    private fun setUpView() {
        taskRecycleView.layoutManager = LinearLayoutManager(context)
        adapter = TaskAdapter(
            touchActionDelegate = touchActionDelegate,
            dataActionDelegate = dataActionDelegate
        ) // Here we specify which argument is
        taskRecycleView.adapter = adapter
    }

    fun updateList(list: List<Task>) {
        adapter.updateList(list)
    }

    fun updateItem(newTask: Task, indexInList: Int, indexInView: Int){
        adapter.onItemUpdated(newTask, indexInList, indexInView)
    }


    fun deleteItem(indexInList: Int, indexInView: Int){
        adapter.onItemDeleted(indexInList, indexInView)
    }

}