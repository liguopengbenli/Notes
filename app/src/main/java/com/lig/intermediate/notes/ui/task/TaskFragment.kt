package com.lig.intermediate.notes.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.Todo
import kotlinx.android.synthetic.main.fragment_task.*

class TaskFragment : Fragment() {
    private var TaskViewModel: TaskViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        TaskViewModel = TaskViewModel()
        val root = inflater.inflate(R.layout.fragment_task, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskRecycleView.layoutManager = LinearLayoutManager(context)
        val adapter = TaskAdapter(mutableListOf(
            Task("Testing 1", mutableListOf(Todo("test1", true), Todo("test2"))),
            Task("Testing 2")
        ))
        taskRecycleView.adapter = adapter
    }

}