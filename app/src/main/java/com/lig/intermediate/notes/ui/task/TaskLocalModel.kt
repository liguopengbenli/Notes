package com.lig.intermediate.notes.ui.task

import android.util.Log
import com.lig.intermediate.notes.application.NoteApplication
import com.lig.intermediate.notes.database.RoomDataBaseClient
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.Todo
import com.lig.intermediate.notes.ui.notes.TIME_OUT
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

class TaskLocalModel @Inject constructor(): ITaskModel {

    private var databaseClient: RoomDataBaseClient = RoomDataBaseClient.getInstance(NoteApplication.instance.applicationContext)

    private suspend fun performOperationWithTimeout(function:()->Unit, callback: SuccessCallback) {

            val job = GlobalScope.async {
                try {
                    // this is specify function to define timeout
                    withTimeout(TIME_OUT){
                        function.invoke()
                    }
                }
                catch (e: Exception){
                    callback.invoke(false)
                }
            }
            job.await()
            callback.invoke(true) // We only invoke true when the job is completed done
    }


    override suspend fun addTask(task: Task, callback: SuccessCallback) {
            val masterJob = GlobalScope.async {
                    try {
                        databaseClient.taskDao().addTask(task)  // add task entity component

                    }catch (e:Exception){
                        callback.invoke(false)
                    }

                addTodoJob(task)    //add to do list component
            }
            masterJob.await()
            callback.invoke(true)

    }

    override suspend fun updateTask(task: Task, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient.taskDao().updateTask(task)}, callback)
    }

    override suspend fun updateTodo(todo: Todo, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient.taskDao().updateTodo(todo)}, callback)

    }

    override suspend fun deleteTask(task: Task, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient.taskDao().deleteTask(task)}, callback)

    }

    private fun addTodoJob(task: Task): Job = GlobalScope.async {
        task.todos.forEach {
                todo -> databaseClient.taskDao().addTodo(todo)
        }
    }


    override suspend fun retrieveTasks(callback: (List<Task>?)->Unit){

            val job = GlobalScope.async {
                withTimeoutOrNull(TIME_OUT){
                    databaseClient.taskDao().retrieveTask()
                }
            }
            // Here job will return a nullable list of task
            callback.invoke(job.await())
        }

}