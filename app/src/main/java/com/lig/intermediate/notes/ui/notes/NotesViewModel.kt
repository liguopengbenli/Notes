package com.lig.intermediate.notes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lig.intermediate.notes.models.Note

class NotesViewModel : ViewModel() {
    private val mText: MutableLiveData<String>
    val text: LiveData<String>
        get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is Notes fragment"
    }

    fun getFakeNote():MutableList<Note> = mutableListOf(
        Note("this is note 1 tesing"),
        Note("This is note 2 testing"),
        Note("this is note 3 tesing")
    )
}