package com.lig.intermediate.notes.ui.task

import android.util.Log
import com.lig.intermediate.notes.application.NoteApplication
import com.lig.intermediate.notes.database.RoomDataBaseClient
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.Todo
import javax.inject.Inject

class TaskLocalModel @Inject constructor(): ITaskModel {

    private var databaseClient: RoomDataBaseClient = RoomDataBaseClient.getInstance(NoteApplication.instance.applicationContext)


    override fun getFakeData(): MutableList<Task> = retrieveTasks().toMutableList()

    override fun addTask(task: Task, callback: SuccessCallback) {
        databaseClient.taskDao().addTask(task)
        addTodoInTask(task)
        callback.invoke(true)

    }

    override fun updateTask(task: Task, callback: SuccessCallback) {
        databaseClient.taskDao().updateTask(task)
        callback.invoke(true)
    }

    override fun updateTodo(todo: Todo, callback: SuccessCallback) {
       databaseClient.taskDao().updateTodo(todo)
        callback.invoke(true)
    }


    override fun deleteTask(task: Task, callback: SuccessCallback) {
        databaseClient.taskDao().deleteTask(task)
        callback.invoke(true)
    }

    private fun addTodoInTask(task: Task){ // we have to save to do in db and @ will fix the fusion
        task.todos.forEach {
            todo -> databaseClient.taskDao().addTodo(todo)
        }
    }

    override fun retrieveTasks(): List<Task>  = databaseClient.taskDao().retrieveTask()

}