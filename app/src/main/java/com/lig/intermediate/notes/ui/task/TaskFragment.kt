package com.lig.intermediate.notes.ui.task

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.Todo
import kotlinx.android.synthetic.main.fragment_task.*

// fragement should be simple so we move code in view
class TaskFragment : Fragment() {
    lateinit var taskViewModel: TaskViewModel
    lateinit var touchActionDelegate: TouchActionDelegate
    lateinit var contentView: TaskListView

    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        context?.let {
            if(it is TouchActionDelegate){ // Navigation activity implement TouchActionDelegate, so its context will be
                touchActionDelegate = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return  inflater.inflate(R.layout.fragment_task, container, false).apply {
            contentView = (this as TaskListView)  // populate content view
        }
    }

    private fun setContentView(){
        contentView.initView(touchActionDelegate, taskViewModel)
    }

    private fun bindViewModel(){
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        //taskViewModel = TaskViewModel()
        taskViewModel.taskListLiveData.observe(viewLifecycleOwner, Observer{taskList->
            contentView.updateList(taskList)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        setContentView()
    }

    interface TouchActionDelegate {
        fun onAddButtonClicked(value: String)
    }

}