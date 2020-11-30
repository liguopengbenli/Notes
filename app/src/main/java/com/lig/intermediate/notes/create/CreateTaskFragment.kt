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
import com.lig.intermediate.notes.foundations.StateChangeTextWatcher
import com.lig.intermediate.notes.views.CreateTodoView
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.android.synthetic.main.view_create_task.view.*
import kotlinx.android.synthetic.main.view_create_todo.view.*
import java.lang.RuntimeException


class CreateTaskFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val view = LayoutInflater.from(context).inflate(R.layout.view_create_todo, containerView, false) as CreateTodoView
        view.todoEditText.addTextChangedListener(object: StateChangeTextWatcher(){
            override fun afterTextChanged(s: Editable?) {
                if(!s.isNullOrEmpty() && previousValue.isNullOrEmpty()){
                    addTodoView()
                } else if(!previousValue.isNullOrEmpty() && s.isNullOrEmpty()){
                    removeTodoView(view)
                }
                super.afterTextChanged(s)
            }
        } )
        containerView.addView(view)
    }

    private fun removeTodoView(view: View){
        containerView.removeView(view)
    }

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