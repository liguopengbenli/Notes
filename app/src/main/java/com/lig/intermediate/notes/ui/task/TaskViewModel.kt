package com.lig.intermediate.notes.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.Todo

class TaskViewModel : ViewModel() {
    private val mText: MutableLiveData<String>
    val text: LiveData<String>
        get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is task fragment"
    }


    fun getFakeData(): MutableList<Task> = mutableListOf(
        Task("Testing 1", mutableListOf(Todo("test1", true), Todo("test2"))),
        Task("Testing 2"),
        Task("Testing three", mutableListOf(Todo("Test A"), Todo("TestB")))
    )
}