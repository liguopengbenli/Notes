package com.lig.intermediate.notes.ui.task

import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.Todo
import javax.inject.Inject

class TaskModel @Inject constructor() {

    fun getFakeData(): MutableList<Task> = mutableListOf(
        Task("Testing 1", mutableListOf(Todo("test1", true), Todo("test2"))),
        Task("Testing 2"),
        Task("Testing three", mutableListOf(Todo("Test A"), Todo("TestB")))
    )
}