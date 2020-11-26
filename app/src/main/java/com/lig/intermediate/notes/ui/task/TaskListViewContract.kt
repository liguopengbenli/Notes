package com.lig.intermediate.notes.ui.task

interface TaskListViewContract {
    fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean)
}