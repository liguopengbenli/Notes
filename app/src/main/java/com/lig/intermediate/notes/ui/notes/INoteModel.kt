package com.lig.intermediate.notes.ui.notes

import com.lig.intermediate.notes.models.Note

typealias SuccessCallback = (Boolean) -> Unit

interface INoteModel {


    //Create Rtrive Update Delive CRUD
    // fun addNote(note:Note, callback:(Boolean)->Unit)
    suspend fun addNote(note:Note, callback: SuccessCallback)
    suspend fun updateNote(note:Note, callback: SuccessCallback)
    suspend fun deleteNote(note:Note, callback: SuccessCallback)
    suspend fun retrieveNotes(callback: (List<Note>?)->Unit )
}