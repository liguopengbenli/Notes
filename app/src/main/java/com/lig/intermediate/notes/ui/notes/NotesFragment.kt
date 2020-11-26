package com.lig.intermediate.notes.ui.notes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.models.Note
import com.lig.intermediate.notes.ui.task.TaskFragment
import com.lig.intermediate.notes.ui.task.TaskListView
import com.lig.intermediate.notes.ui.task.TaskViewModel
import com.lig.intermediate.notes.views.NoteView
import kotlinx.android.synthetic.main.fragment_notes.*

// fragment is a portion of UI like sub activity and can be resued
class NotesFragment : Fragment() {
    lateinit var notesViewModel: NotesViewModel
    lateinit var noteActionDelegate: NoteActionDelegate
    lateinit var contentView: NoteListView

    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        context?.let {
            if(it is NotesFragment.NoteActionDelegate){ // Navigation activity implement TouchActionDelegate, so its context will be
                noteActionDelegate = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false).apply {
            contentView = (this as NoteListView)
        }
    }

    private fun bindViewModel(){
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java) // using this way for latter dependency testing propose
        //notesViewModel = NotesViewModel()
        notesViewModel.noteListLiveData.observe(viewLifecycleOwner, Observer{ noteList->
            contentView.updateList(noteList)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        setContentView()
    }


    private fun setContentView(){
        contentView.initView(noteActionDelegate, notesViewModel)
    }

    interface NoteActionDelegate {
        fun onAddButtonClicked(value: String)
    }

}