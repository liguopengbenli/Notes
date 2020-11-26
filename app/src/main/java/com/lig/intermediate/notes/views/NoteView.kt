package com.lig.intermediate.notes.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.lig.intermediate.notes.models.Note
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.ui.notes.NoteAdapter
import com.lig.intermediate.notes.ui.notes.NotesFragment
import com.lig.intermediate.notes.ui.task.TaskAdapter
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.android.synthetic.main.fragment_notes.view.*
import kotlinx.android.synthetic.main.fragment_task.view.*
import kotlinx.android.synthetic.main.item_note.view.*

class NoteView @JvmOverloads constructor( // make sure work for java
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun initView(note: Note) {
        item_note_text.text = note.description
    }

}