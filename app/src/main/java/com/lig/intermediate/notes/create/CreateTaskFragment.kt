package com.lig.intermediate.notes.create

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.foundations.ApplicationScope
import com.lig.intermediate.notes.foundations.NullFieldChecker
import com.lig.intermediate.notes.foundations.StateChangeTextWatcher
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.Todo
import com.lig.intermediate.notes.ui.task.ITaskModel
import com.lig.intermediate.notes.ui.task.TaskLocalModel
import com.lig.intermediate.notes.views.CreateTodoView
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.android.synthetic.main.view_create_task.view.*
import kotlinx.android.synthetic.main.view_create_todo.view.*
import toothpick.Toothpick
import java.lang.RuntimeException
import javax.inject.Inject

private const val MAX_TODO_COUNT = 5

class CreateTaskFragment : Fragment() {
    @Inject
    lateinit var model: ITaskModel // it's always better to inject interface

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toothpick.inject(this, ApplicationScope.scope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createTaskView.taskEditTask.addTextChangedListener(object : StateChangeTextWatcher(){
            override fun afterTextChanged(s: Editable?) {
                if(!s.isNullOrEmpty() && previousValue.isNullOrEmpty()){
                    addTodoView()
                }else if(!previousValue.isNullOrEmpty() && s.isNullOrEmpty()){
                    removeTodoView(containerView.getChildAt(containerView.childCount -1))
                }// no need because if task empty not allow to save
                super.afterTextChanged(s)
            }
        })
    }

    private fun addTodoView(){
        if(canAddTodo()){
            val view = (LayoutInflater.from(context).inflate(R.layout.view_create_todo, containerView, false) as CreateTodoView).apply {
                todoEditText.addTextChangedListener(object: StateChangeTextWatcher(){
                    override fun afterTextChanged(s: Editable?) {
                        if(!s.isNullOrEmpty() && previousValue.isNullOrEmpty()){
                            addTodoView()
                        } else if(!previousValue.isNullOrEmpty() && s.isNullOrEmpty()){
                            removeTodoView(this@apply)
                            if(containerView.childCount == MAX_TODO_COUNT){ // edge case
                                addTodoView()
                            }
                        }
                        super.afterTextChanged(s)
                    }
                } )
            }
            containerView.addView(view)
        }
    }

    private fun removeTodoView(view: View){
        containerView.removeView(view)
    }

    private fun isTaskEmpty(): Boolean = createTaskView.taskEditTask.editableText.isNullOrEmpty()

    fun saveTask(callback: (Boolean) -> Unit){
        createTask()?.let {task->
            model.addTask(task){success->
                callback.invoke(success)
            }
        } ?: callback.invoke(false) // not Created
    }


    fun createTask(): Task? {
        if(!isTaskEmpty()){
            containerView.run {
                var taskField: String? = null
                val todoList: MutableList<Todo> = mutableListOf()
                for(i in 0 until containerView.childCount){ //exclusive the last value
                    if(i==0){
                        taskField = containerView.getChildAt(i).taskEditTask.editableText?.toString()
                    }else{
                        if(!containerView.getChildAt(i).todoEditText.editableText.isNullOrEmpty()){
                            todoList.add(
                                Todo(description = containerView.getChildAt(i).todoEditText.editableText.toString())
                            )
                        }
                    }
                }
                return taskField?.let{
                    Task(it, todoList)
                }
            }
        }else {
            return null
        }
    }

    private fun canAddTodo():Boolean = containerView.childCount < MAX_TODO_COUNT + 1 &&
            !(containerView.getChildAt(containerView.childCount-1) as NullFieldChecker).hasNullField() // if last item null don't add new item

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnFragmentInteractionListener){
            listener = context
        }else {
            throw RuntimeException(context.toString() + "must implement onFragmentInterractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction()
    }

    companion object {
        fun newInstance() = CreateTaskFragment()
    }
}