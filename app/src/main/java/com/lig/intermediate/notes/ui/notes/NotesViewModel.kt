package com.lig.intermediate.notes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lig.intermediate.notes.models.Note
import toothpick.Toothpick
import javax.inject.Inject

class NotesViewModel : ViewModel(), NoteListViewContract {
    private val _noteListLiveData: MutableLiveData<List<Note>> = MutableLiveData()
    //private val model: NoteModel = NoteModel()
    val noteListLiveData: LiveData<List<Note>> =
        _noteListLiveData // cast mutable Livadata to Live data

    @Inject
    lateinit var localModel: NoteLocalModel

    init {
        val scope = Toothpick.openScope(this)// any scope name here is class name?
        Toothpick.inject(this@NotesViewModel, scope)
        _noteListLiveData.postValue(localModel.getFakeNote())
    }


}