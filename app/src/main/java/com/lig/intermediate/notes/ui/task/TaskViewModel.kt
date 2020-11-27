package com.lig.intermediate.notes.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lig.intermediate.notes.models.Task
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class TaskViewModel : ViewModel(), TaskListViewContract {
    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> =
        _taskListLiveData // read only access, live data is not mutable
    //private val model: TaskModel = TaskModel()
    @Inject
    lateinit var localModel: ITaskModel

    init {
        val scope: Scope = Toothpick.openScope("FirstScope")
        Toothpick.inject(this@TaskViewModel, scope)

        // _taskListLiveData.value = getFakeData() synchro not recommended
        _taskListLiveData.postValue(localModel.getFakeData()) // asynchrone
    }


    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskListLiveData.value?.get(taskIndex)?.todos?.get(todoIndex)?.isComplete = isComplete
    }


}