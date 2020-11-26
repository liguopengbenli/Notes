package com.lig.intermediate.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.models.Task
import kotlinx.android.synthetic.main.item_task.view.*


class TaskView @JvmOverloads constructor( // make sure work for java
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var task: Task

    fun initView(task: Task, todoCheckCallback: (Int, Boolean) -> Unit) {
        this.task = task

        item_task_title.text = task.title
        // for each view_todo we inflate a view and populate and attach to parent container todoContainer
        task.todos.forEachIndexed { todoIndex, todo ->
            val todoView = (LayoutInflater.from(context)
                .inflate(R.layout.view_todo, todoContainer, false) as TodoView).apply {
                initView(todo) { isChecked ->

                    todoCheckCallback.invoke(todoIndex, isChecked)
                    if (isTaskComplete()) {
                        createStrikeThrough()
                    } else {
                        removeStrikeThrough()
                    }
                } // passing function as parameter
            }
            // R.layout.view_todo : to get resources from here
            // view.todoContainer: parent view group
            todoContainer.addView(todoView) // attach to parent
        }
    }

    private fun isTaskComplete(): Boolean = task.todos.filter { !it.isComplete }
        .isEmpty() // filter literate all elements and check the condition


    // Paints flags are like series of bits, put one bit to 1 using logic operation
    private fun createStrikeThrough() {
        item_task_title.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun removeStrikeThrough() {
        item_task_title.apply {
            paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

}