package com.lig.intermediate.notes.ui.task

import com.lig.intermediate.notes.models.Note
import com.lig.intermediate.notes.models.Task

typealias SuccessCallback = (Boolean) -> Unit


interface ITaskModel {

    //Create Rtrive Update Delive CRUD

    fun addTask(task: Task, callback: SuccessCallback)
    fun updateTask(task: Task, callback: SuccessCallback)
    fun deleteTask(task: Task, callback: SuccessCallback)
    fun retrieveTasks(): List<Task>
    fun getFakeData(): MutableList<Task>
}