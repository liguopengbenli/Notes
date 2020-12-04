package com.lig.intermediate.notes.foundations

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

// use generic to get any type of data

abstract class BaseRecycleAdapter<T>(
    protected val masterList: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun onItemDeleted(indexInList: Int, indexInView: Int){
        masterList.removeAt(indexInList)
        notifyItemRemoved(indexInView)  // just notify partially, nice performance
    }

    fun onItemUpdated(newItem: T, indexInList: Int, indexInView: Int){
        masterList[indexInList] = newItem
        notifyItemChanged(indexInView)
    }

    fun updateList(list: List<T>) {
        //val result = DiffUtil.calculateDiff(DiffUtilCallbackImpl<T>(masterList, list))
        masterList.clear()
        masterList.addAll(list)
        notifyDataSetChanged()
        //result.dispatchUpdatesTo(this) // //update all the list not optimal, Use diffUtil to update partially
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AddButtonViewHolder) {
            holder.onBind(Unit, position - 1)
        } else {
            (holder as BaseViewHolder<T>).onBind(masterList[position - 1], position - 1)
        }
    }

    override fun getItemCount(): Int = masterList.size + 1 // position start with index 1

    abstract class BaseViewHolder<E>(val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(data: E, listIndex: Int)
    }

    abstract class AddButtonViewHolder(view: View) : BaseViewHolder<Unit>(view)

    // according to position to return a custom type of item
    override fun getItemViewType(position: Int): Int = if (position == 0) {
        TYPE_ADD_BUTTON
    } else {
        TYPE_INFO
    }

    companion object {
        const val TYPE_ADD_BUTTON = 0
        const val TYPE_INFO = 1
    }

/**
 * DiffUtil is a utility class that calculates the difference between two lists and outputs a
 * list of update operations that converts the first list into the second one.*/

    class DiffUtilCallbackImpl<T>(val oldList: List<T>, val newList: List<T>): DiffUtil.Callback(){
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] == newList[newItemPosition] // the id but we don't have here

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] == newList[newItemPosition]
    }

}