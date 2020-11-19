package com.lig.intermediate.notes.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lig.intermediate.notes.R

class TaskFragment : Fragment() {
    private var TaskViewModel: TaskViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        TaskViewModel = TaskViewModel()
        val root = inflater.inflate(R.layout.fragment_task, container, false)
        val textView = root.findViewById<TextView>(R.id.text_task)
        TaskViewModel!!.text.observe(viewLifecycleOwner, { s -> textView.text = s })
        return root
    }
}