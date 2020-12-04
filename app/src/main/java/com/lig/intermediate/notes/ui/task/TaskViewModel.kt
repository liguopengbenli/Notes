package com.lig.intermediate.notes.ui.task

import android.telephony.gsm.GsmCellLocation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lig.intermediate.notes.foundations.ApplicationScope
import com.lig.intermediate.notes.models.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Scope
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class TaskViewModel : ViewModel(), TaskListViewContract {
    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData // cast to read only access, live data is not mutable
    //private val model: TaskModel = TaskModel()

    private val _stateChangeLiveData: MutableLiveData<ItemState> = MutableLiveData()
    val stateChangedLiveData: LiveData<ItemState> = _stateChangeLiveData

    @Inject
    lateinit var localModel: ITaskModel

    init {

        Toothpick.inject(this@TaskViewModel, ApplicationScope.scope)
        // _taskListLiveData.value = getFakeData() synchro not recommended
       loadData()
    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        GlobalScope.launch {
            _taskListLiveData.value?.let {tasklist->
                val todo = tasklist[taskIndex].todos[todoIndex]
                todo.apply {
                    this.isComplete = isComplete        // we have to be explicit here
                    this.taskId = tasklist[taskIndex].uid
                }
                localModel.updateTodo(todo){success->
                    //loadData()
                    _stateChangeLiveData.postValue(ItemState.ItemUpdated(
                        newTask = tasklist[taskIndex],
                        il = taskIndex,
                        iv = taskIndex + 1
                    ))
                }
            }
        }
    }

    override fun onTaskDeleted(taskIndex: Int) {
        GlobalScope.launch {
            _taskListLiveData.value?.let {taskList->
                localModel.deleteTask(taskList[taskIndex]){success->
                    //loadData()
                    _stateChangeLiveData.postValue(ItemState.ItemDeleted(
                        il = taskIndex,
                        iv = taskIndex + 1
                    ))
                }
            }
        }

    }

    fun loadData(){
        GlobalScope.launch {
            localModel.retrieveTasks {nullableList->
                nullableList?.let {
                    _taskListLiveData.postValue(it.toMutableList()) // asynchrone
                }

            }
        }
    }

  sealed class ItemState(val indexInList: Int, val indexInView: Int){
      class ItemUpdated(val newTask: Task, il: Int, iv: Int): ItemState(il, iv) // we can only extend seal class inside of sealed class
      class ItemDeleted(il: Int, iv: Int): ItemState(il, iv)
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