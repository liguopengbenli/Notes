package com.lig.intermediate.notes.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lig.intermediate.notes.R

// fragment is a portion of UI like sub activity and can be resued
class NotesFragment : Fragment() {
    private var notesViewModel: NotesViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        notesViewModel = NotesViewModel()
        val root = inflater.inflate(R.layout.fragment_notes, container, false)
        val textView = root.findViewById<TextView>(R.id.text_notes)
        notesViewModel!!.text.observe(viewLifecycleOwner, { s -> textView.text = s })
        return root
    }
}