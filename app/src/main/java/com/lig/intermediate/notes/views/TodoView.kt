package com.lig.intermediate.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.models.Todo
import kotlinx.android.synthetic.main.view_todo.view.*

class TodoView @JvmOverloads constructor( // make sure work for java
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun initView(todo: Todo, callback: (() -> Unit)? = null) {
        descriptionView.text = todo.description
        completeCheckBox.isChecked = todo.isComplete
        if (todo.isComplete) {
            createStrikeThrough()
        }
        setUpCheckStateListener(todo, callback)
    }

    // passing a function into a function: functional program like JS
    // By defaut we define this function null
    fun setUpCheckStateListener(todo: Todo, callback: (() -> Unit)? = null) {
        completeCheckBox.setOnCheckedChangeListener { _, isCheck: Boolean -> // _ means the parameter won't be used
            todo.isComplete = isCheck
            callback?.invoke() // if the callback is null will not trigger this
            if (isCheck) {
                createStrikeThrough()
            } else {
                removeStrikeThrough()
            }
        }
    }

    // Paints flags are like series of bits, put one bit to 1 using logic operation
    private fun createStrikeThrough() {
        descriptionView.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun removeStrikeThrough() {
        descriptionView.apply {
            paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}