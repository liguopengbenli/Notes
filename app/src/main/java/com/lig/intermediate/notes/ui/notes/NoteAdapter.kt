package com.lig.intermediate.notes.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.models.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(
    private val noteList: MutableList<Note> = mutableListOf()
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(noteList[position])
    }

    override fun getItemCount(): Int = noteList.size

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun onBind(note: Note){
            view.item_note_text.text = note.description
        }
    }

}