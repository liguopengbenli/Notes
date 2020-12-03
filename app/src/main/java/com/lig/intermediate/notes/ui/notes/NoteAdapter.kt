package com.lig.intermediate.notes.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.foundations.BaseRecycleAdapter
import com.lig.intermediate.notes.models.Note
import com.lig.intermediate.notes.navigation.NavigationActivity.Companion.FRAGEMENT_VALUE_NOTE
import com.lig.intermediate.notes.views.NoteView
import kotlinx.android.synthetic.main.view_add_button.view.*

class NoteAdapter(
    noteList: MutableList<Note> = mutableListOf(),
    val noteActionDelegate: NotesFragment.NoteActionDelegate,
    val dataActionDelegate: NoteListViewContract
) : BaseRecycleAdapter<Note>(noteList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == TYPE_ADD_BUTTON) {
            AddButtonViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.view_add_button, parent, false)
            )
        } else {
            NoteViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
            )
        }

    inner class NoteViewHolder(view: View) : BaseViewHolder<Note>(view) {
        override fun onBind(data: Note, listIndex: Int) {
            (view as NoteView).initView(data){
                dataActionDelegate.onDeleteNote(masterList[listIndex]) // only trigger when click delete button
            }
        }
    }

    inner class AddButtonViewHolder(view: View) : BaseRecycleAdapter.AddButtonViewHolder(view) {
        override fun onBind(data: Unit, listIndex: Int) {
            view.buttonText.text = view.context.getString(R.string.add_button_note)
            view.setOnClickListener {
                noteActionDelegate.onAddButtonClicked(FRAGEMENT_VALUE_NOTE)
            }
        }
    }


}