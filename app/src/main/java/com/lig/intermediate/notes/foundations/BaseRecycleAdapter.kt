package com.lig.intermediate.notes.foundations

import android.view.View
import androidx.recyclerview.widget.RecyclerView
// use generic to get any type of data
abstract class BaseRecycleAdapter<T>(
    protected val masterList: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<T>).onBind(masterList[position])
    }

    override fun getItemCount(): Int  = masterList.size

    abstract class BaseViewHolder<E>(val view: View): RecyclerView.ViewHolder(view){
        abstract fun onBind(data: E)
    }

}