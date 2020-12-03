package com.lig.intermediate.notes.ui.notes

import android.util.Log
import com.lig.intermediate.notes.application.NoteApplication
import com.lig.intermediate.notes.database.RoomDataBaseClient
import com.lig.intermediate.notes.models.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class NoteLocalModel @Inject constructor() : INoteModel {

    private var databaseClient: RoomDataBaseClient = RoomDataBaseClient.getInstance(NoteApplication.instance.applicationContext)
    

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
        GlobalScope.launch {
           val dbJob = this.async {
               try {
                   databaseClient.noteDao().updateNote(note)
                   true
               }catch(e:Exception) {
                   false
               }
            }
            callback.invoke( dbJob.await()) //return boolean
        }
    }

    override fun deleteNote(note: Note, callback: SuccessCallback) {
        GlobalScope.launch {
            val dbJob = async {
                try {
                    databaseClient.noteDao().deleteNote(note)
                    true
                }catch(e:Exception) {
                    false
                }
            }
            callback.invoke(dbJob.await())
        }
    }

    override fun retrieveNotes(callback: (List<Note>?)->Unit )
    {
        GlobalScope.launch {
                try {
                    callback.invoke(databaseClient.noteDao().retriveNotes())
                }catch(e:Exception) {
                    callback.invoke(null)
                }
        }
    }


}