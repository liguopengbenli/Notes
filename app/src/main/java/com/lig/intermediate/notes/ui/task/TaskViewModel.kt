package com.lig.intermediate.notes.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.Todo

class TaskViewModel : ViewModel(), TaskListViewContract {
    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData // read only access, live data is not mutable

    init {
        // _taskListLiveData.value = getFakeData() synchro not recommended
        _taskListLiveData.postValue(getFakeData()) // asynchrone
    }


    private fun getFakeData(): MutableList<Task> = mutableListOf(
        Task("Testing 1", mutableListOf(Todo("test1", true), Todo("test2"))),
        Task("Testing 2"),
        Task("Testing three", mutableListOf(Todo("Test A"), Todo("TestB")))
    )

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
            _taskListLiveData.value?.get(taskIndex)?.todos?.get(todoIndex)?.isComplete = isComplete
    }


}