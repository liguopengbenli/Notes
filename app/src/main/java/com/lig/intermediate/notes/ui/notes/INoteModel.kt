package com.lig.intermediate.notes.ui.notes

import com.lig.intermediate.notes.models.Note

typealias SuccessCallback = (Boolean) -> Unit

interface INoteModel {


    //Create Rtrive Update Delive CRUD
    // fun addNote(note:Note, callback:(Boolean)->Unit)
    fun addNote(note:Note, callback: SuccessCallback)
    fun updateNote(note:Note, callback: SuccessCallback)
    fun deleteNote(note:Note, callback: SuccessCallback)
    fun retrieveNotes(callback: (List<Note>?)->Unit )
}