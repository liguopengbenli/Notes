package com.lig.intermediate.notes.ui.notes

import com.lig.intermediate.notes.models.Note

class NoteModel {
    fun getFakeNote(): MutableList<Note> = mutableListOf(
        Note("this is note 1 tesing"),
        Note("This is note 2 testing"),
        Note("this is note 3 tesing")
    )
}