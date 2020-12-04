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

    fun initView(task: Task, todoCheckCallback: (Int, Boolean) -> Unit, deleteCallback: () -> Unit) {
        this.task = task
        resetCildView()
        initTaskLine(deleteCallback)
        addChildViews(todoCheckCallback)

    }

    private fun resetCildView(){
        todoContainer.removeAllViewsInLayout() // reset Child view
    }

    private fun initTaskLine(deleteCallback: () -> Unit){
        item_task_title.text = task.title
        imageButton.setOnClickListener {
            deleteCallback.invoke()
        }

        if (isTaskComplete()) {
            this@TaskView.item_task_title.setStrikeThrough()
        } else {
            this@TaskView.item_task_title.removeStrikeThrough()
        }
    }

    private fun addChildViews(todoCheckCallback: (Int, Boolean) -> Unit){
        // for each view_todo we inflate a view and populate and attach to parent container todoContainer
        task.todos.forEachIndexed { todoIndex, todo ->
            val todoView = (LayoutInflater.from(context)
                .inflate(R.layout.view_todo, todoContainer, false) as TodoView).apply {
                initView(todo) { isChecked ->

                    todoCheckCallback.invoke(todoIndex, isChecked)
                } // passing function as parameter
            }
            // R.layout.view_todo : to get resources from here
            // view.todoContainer: parent view group
            todoContainer.addView(todoView) // attach to parent
        }
    }

    private fun isTaskComplete(): Boolean = task.todos.filter { !it.isComplete }.isEmpty()
    // filter literate all elements and check the condition




}