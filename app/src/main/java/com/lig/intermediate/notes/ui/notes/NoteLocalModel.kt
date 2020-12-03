package com.lig.intermediate.notes.ui.notes

import android.util.Log
import com.lig.intermediate.notes.application.NoteApplication
import com.lig.intermediate.notes.database.RoomDataBaseClient
import com.lig.intermediate.notes.models.Note
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

const val TIME_OUT = 3000L
class NoteLocalModel @Inject constructor() : INoteModel {

    private var databaseClient: RoomDataBaseClient = RoomDataBaseClient.getInstance(NoteApplication.instance.applicationContext)


//        mutableListOf(
//        Note(description = "this is note 1 tesing"),
//        Note(description="This is note 2 testing"),
//        Note(description="this is note 3 tesing")
//    )

    private fun performOperationWithTimeout(function:()->Unit, callback: SuccessCallback) {
        GlobalScope.launch {
                val job = async {
                    try {
                            // this is specify function to define timeout
                            withTimeout(TIME_OUT){
                                function.invoke()
                            }
                        }
                    catch (e:Exception){
                       callback.invoke(false)
                    }
            }
            job.await()
            callback.invoke(true) // We only invoke true when the job is completed done
        }
    }

    override fun addNote(note: Note, callback: SuccessCallback) {
        performOperationWithTimeout(
            { databaseClient.noteDao().addNote(note)},
            callback
        )
    }

    override fun updateNote(note: Note, callback: SuccessCallback) {
        performOperationWithTimeout(
            {databaseClient.noteDao().updateNote(note)},
            callback
        )
    }

    override fun deleteNote(note: Note, callback: SuccessCallback) {
        performOperationWithTimeout(
            {databaseClient.noteDao().deleteNote(note)},
            callback
        )
    }

    override fun retrieveNotes(callback: (List<Note>?)->Unit )
    {
        GlobalScope.launch {
            val job = async {
                withTimeoutOrNull(TIME_OUT){
                    databaseClient.noteDao().retriveNotes()
                }
            }
            // Here job will return a nullable list of note
            callback.invoke(job.await())
        }
    }


}