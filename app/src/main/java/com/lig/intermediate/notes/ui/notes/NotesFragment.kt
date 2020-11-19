package com.lig.intermediate.notes.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.models.Note
import kotlinx.android.synthetic.main.fragment_notes.*

// fragment is a portion of UI like sub activity and can be resued
class NotesFragment : Fragment() {
    private var notesViewModel: NotesViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        notesViewModel = NotesViewModel()
        val root = inflater.inflate(R.layout.fragment_notes, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteRecycleView.layoutManager = LinearLayoutManager(context)
        val adapter = NoteAdapter(mutableListOf(
            Note("this is note 1 tesing"),
            Note("This is note 2 testing"),
            Note("this is note 3 tesing")
        ))
        noteRecycleView.adapter = adapter
    }


}