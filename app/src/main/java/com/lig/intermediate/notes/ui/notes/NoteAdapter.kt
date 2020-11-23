package com.lig.intermediate.notes.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.foundations.BaseRecycleAdapter
import com.lig.intermediate.notes.models.Note
import com.lig.intermediate.notes.views.NoteView
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(
     noteList: MutableList<Note> = mutableListOf()
): BaseRecycleAdapter<Note>(noteList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    class ViewHolder(view: View): BaseViewHolder<Note>(view){
        override fun onBind(data: Note) {
            (view as NoteView).initView(data)
        }
    }

}