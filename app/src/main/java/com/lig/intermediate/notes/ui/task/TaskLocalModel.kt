package com.lig.intermediate.notes.ui.task

import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.Todo
import javax.inject.Inject

class TaskLocalModel @Inject constructor(): ITaskModel {

    override fun getFakeData(): MutableList<Task> = mutableListOf(
        Task("Testing 1", mutableListOf(Todo("test1", true), Todo("test2"))),
        Task("Testing 2"),
        Task("Testing three", mutableListOf(Todo("Test A"), Todo("TestB")))
    )

    override fun addTask(task: Task, callback: SuccessCallback) {
        TODO("Not yet implemented")
    }

    override fun updateTask(task: Task, callback: SuccessCallback) {
        TODO("Not yet implemented")
    }

    override fun deleteTask(task: Task, callback: SuccessCallback) {
        TODO("Not yet implemented")
    }

    override fun retrieveTasks(): List<Task> {
        TODO("Not yet implemented")
    }
}