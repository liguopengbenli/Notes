package com.lig.intermediate.notes.ui.task

import com.lig.intermediate.notes.models.Note
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.Todo

typealias SuccessCallback = (Boolean) -> Unit


interface ITaskModel {

    //Create Rtrive Update Delive CRUD

    fun addTask(task: Task, callback: SuccessCallback)
    fun updateTask(task: Task, callback: SuccessCallback)
    fun updateTodo(todo: Todo, callback: SuccessCallback)
    fun deleteTask(task: Task, callback: SuccessCallback)
    fun retrieveTasks(callback: (List<Task>?)->Unit)
}