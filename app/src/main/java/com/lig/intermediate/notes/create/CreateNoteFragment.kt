package com.lig.intermediate.notes.create

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.foundations.ApplicationScope
import com.lig.intermediate.notes.foundations.NullFieldChecker
import com.lig.intermediate.notes.models.Note
import com.lig.intermediate.notes.ui.notes.INoteModel
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import java.lang.RuntimeException
import javax.inject.Inject

class CreateNoteFragment : Fragment(), NullFieldChecker {
    private var listener: CreateTaskFragment.OnFragmentInteractionListener? = null
    @Inject
    lateinit var model: INoteModel

    @Inject
    lateinit var stateModel: StateModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, ApplicationScope.scope)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                stateModel.updateState(s.toString())
            }
        })
    }

    fun saveNote(callback: (Boolean) -> Unit){
        GlobalScope.launch {
            createNote()?.let {note->
                model.addNote(note){success->
                    callback.invoke(success)
                }
            }?: callback.invoke(false)
        }
    }

    private fun createNote(): Note? = if (!hasNullField()){
             Note(description= noteEditText.editableText.toString())
        }else{
            null
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

    override fun hasNullField(): Boolean = noteEditText.editableText.isNullOrEmpty()
}