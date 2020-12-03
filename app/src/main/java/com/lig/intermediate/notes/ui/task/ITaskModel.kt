package com.lig.intermediate.notes.ui.task

import com.lig.intermediate.notes.models.Note
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.Todo

typealias SuccessCallback = (Boolean) -> Unit


interface ITaskModel {

    //Create Rtrive Update Delive CRUD

    suspend fun addTask(task: Task, callback: SuccessCallback)
    suspend fun updateTask(task: Task, callback: SuccessCallback)
    suspend fun updateTodo(todo: Todo, callback: SuccessCallback)
    suspend fun deleteTask(task: Task, callback: SuccessCallback)
    suspend fun retrieveTasks(callback: (List<Task>?)->Unit)
}