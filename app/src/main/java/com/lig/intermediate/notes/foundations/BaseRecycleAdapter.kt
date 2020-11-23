package com.lig.intermediate.notes.foundations

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lig.intermediate.notes.ui.task.TaskAdapter

// use generic to get any type of data
abstract class BaseRecycleAdapter<T>(
    protected val masterList: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is AddButtonViewHolder){
               holder.onBind(Unit)
        }else {
            (holder as BaseViewHolder<T>).onBind(masterList[position - 1])
        }
    }

    override fun getItemCount(): Int  = masterList.size + 1 // position start with index 1

    abstract class BaseViewHolder<E>(val view: View): RecyclerView.ViewHolder(view){
        abstract fun onBind(data: E)
    }

    abstract class AddButtonViewHolder(view: View): BaseViewHolder<Unit>(view)

    // according to position to return a custom type of item
    override fun getItemViewType(position: Int): Int = if(position == 0) {
        TYPE_ADD_BUTTON
    }else{
        TYPE_INFO
    }

    companion object{
        const val TYPE_ADD_BUTTON = 0
        const val TYPE_INFO = 1
    }

}