package com.lig.intermediate.notes.ui.notes

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.lig.intermediate.notes.models.Note
import kotlinx.android.synthetic.main.fragment_notes.view.*

class NoteListView @JvmOverloads constructor( // make sure work for java
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var adapter: NoteAdapter
    private lateinit var noteActionDelegate: NotesFragment.NoteActionDelegate
    private lateinit var dataActionDelegate: NoteListViewContract

    fun initView(
        noteActionDelegate: NotesFragment.NoteActionDelegate,
        daDelegate: NoteListViewContract
    ) {
        this.noteActionDelegate = noteActionDelegate
        this.dataActionDelegate = daDelegate
        setUpView()
    }

    private fun setUpView() {
        noteRecycleView.layoutManager = LinearLayoutManager(context)
        adapter = NoteAdapter(noteActionDelegate = noteActionDelegate, dataActionDelegate = dataActionDelegate)
        noteRecycleView.adapter = adapter
    }

    fun updateList(list: List<Note>) {
        adapter.updateList(list)
    }


}