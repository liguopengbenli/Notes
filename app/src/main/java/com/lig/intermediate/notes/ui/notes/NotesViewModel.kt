package com.lig.intermediate.notes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lig.intermediate.notes.foundations.ApplicationScope
import com.lig.intermediate.notes.models.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class NotesViewModel : ViewModel(), NoteListViewContract {
    private val _noteListLiveData: MutableLiveData<List<Note>> = MutableLiveData()
    //private val model: NoteModel = NoteModel()
    val noteListLiveData: LiveData<List<Note>> =
        _noteListLiveData // cast mutable Livadata to Live data

    @Inject
    lateinit var localModel: INoteModel

    init {
        Toothpick.inject(this@NotesViewModel, ApplicationScope.scope)
        loadData()
    }

    fun loadData(){
        //Get the data with callback function
        GlobalScope.launch {
            localModel.retrieveNotes { nullableList->
                nullableList?.let {
                    _noteListLiveData.postValue(it)
                }
            }
        }
    }

    override fun onDeleteNote(note: Note) {
        GlobalScope.launch {
            localModel.deleteNote(note){
                if(it){
                    loadData() // if callback true reload data
                }
            }
        }
    }


}