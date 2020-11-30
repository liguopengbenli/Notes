package com.lig.intermediate.notes.create

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lig.intermediate.notes.R
import java.lang.RuntimeException

class CreateNoteFragment : Fragment() {
    private var listener: CreateTaskFragment.OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is CreateTaskFragment.OnFragmentInteractionListener){
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
        fun newInstance() = CreateNoteFragment()
    }
}