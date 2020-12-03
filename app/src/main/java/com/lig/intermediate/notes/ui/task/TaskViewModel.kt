package com.lig.intermediate.notes.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lig.intermediate.notes.foundations.ApplicationScope
import com.lig.intermediate.notes.models.Task
import toothpick.Scope
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class TaskViewModel : ViewModel(), TaskListViewContract {
    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> =
        _taskListLiveData // read only access, live data is not mutable
    //private val model: TaskModel = TaskModel()
    @Inject
    lateinit var localModel: ITaskModel

    init {

        Toothpick.inject(this@TaskViewModel, ApplicationScope.scope)
        // _taskListLiveData.value = getFakeData() synchro not recommended
       loadData()
    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskListLiveData.value?.let {
            val todo = it[taskIndex].todos[todoIndex]
            todo.apply {
                this.isComplete = isComplete        // we have to be explicit here
                this.taskId = it[taskIndex].uid
            }
            localModel.updateTodo(todo){
                loadData()
            }
        }
        _taskListLiveData.value?.get(taskIndex)?.todos?.get(todoIndex)?.isComplete = isComplete
    }

    override fun onTaskDeleted(taskIndex: Int) {
        _taskListLiveData.value?.let {
            localModel.deleteTask(it[taskIndex]){
                loadData()
            }
        }
    }

    fun loadData(){
        _taskListLiveData.postValue(localModel.retrieveTasks().toMutableList()) // asynchrone
    }




}




/*

    class TestModel: ITaskModel{
        override fun addTask(task: Task, callback: SuccessCallback) {

        }

        override fun updateTask(task: Task, callback: SuccessCallback) {

        }

        override fun deleteTask(task: Task, callback: SuccessCallback) {

        }

        override fun retrieveTasks(): List<Task> {

        }

        override fun getFakeData(): MutableList<Task> = mutableListOf(
            Task("Test Model Task!")
        )
    }


     val taskViewModelScope = Toothpick.openScopes(ApplicationScope.scope, this)
        taskViewModelScope.installModules(
            Module().apply {
                bind(ITaskModel::class.java).toInstance(TestModel()) // Here is override binding
            }
        )

 */