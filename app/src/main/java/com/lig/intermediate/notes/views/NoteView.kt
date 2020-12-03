package com.lig.intermediate.notes.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.lig.intermediate.notes.models.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteView @JvmOverloads constructor( // make sure work for java
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun initView(note: Note, deleteButtonClickedCallback:()-> Unit) {
        item_note_text.text = note.description
        imageButton.setOnClickListener {
            deleteButtonClickedCallback.invoke()
        }
    }

}