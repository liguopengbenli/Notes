package com.lig.intermediate.notes.ui.notes

import android.util.Log
import com.lig.intermediate.notes.application.NoteApplication
import com.lig.intermediate.notes.database.RoomDataBaseClient
import com.lig.intermediate.notes.models.Note
import javax.inject.Inject

class NoteLocalModel @Inject constructor() : INoteModel {

    private var databaseClient: RoomDataBaseClient = RoomDataBaseClient.getInstance(NoteApplication.instance.applicationContext)


    override fun getFakeNote(): MutableList<Note> = retrieveNotes().toMutableList()


//        mutableListOf(
//        Note(description = "this is note 1 tesing"),
//        Note(description="This is note 2 testing"),
//        Note(description="this is note 3 tesing")
//    )

    override fun addNote(note: Note, callback: SuccessCallback) {
        databaseClient.noteDao().addNote(note)
        callback.invoke(true)
    }

    override fun updateNote(note: Note, callback: SuccessCallback) {
        databaseClient.noteDao().updateNote(note)
        callback.invoke(true)
    }

    override fun deleteNote(note: Note, callback: SuccessCallback) {
        databaseClient.noteDao().deleteNote(note)
        callback.invoke(true)
    }

    override fun retrieveNotes(): List<Note>  = databaseClient.noteDao().retriveNotes()


}