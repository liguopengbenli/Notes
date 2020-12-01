package com.lig.intermediate.notes.ui.notes

import android.util.Log
import com.lig.intermediate.notes.models.Note
import javax.inject.Inject

class NoteLocalModel @Inject constructor() : INoteModel {

    override fun getFakeNote(): MutableList<Note> = mutableListOf(
        Note("this is note 1 tesing"),
        Note("This is note 2 testing"),
        Note("this is note 3 tesing")
    )

    override fun addNote(note: Note, callback: SuccessCallback) {
        Log.d("NoteLocalModel", note.toString())
        callback.invoke(true)
    }

    override fun updateNote(note: Note, callback: SuccessCallback) {
        TODO("Not yet implemented")
    }

    override fun deleteNote(note: Note, callback: SuccessCallback) {
        TODO("Not yet implemented")
    }

    override fun retrieveNotes(): List<Note> {
        TODO("Not yet implemented")
    }
}