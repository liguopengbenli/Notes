package com.lig.intermediate.notes.views

import android.graphics.Paint
import android.widget.TextView

// write extension function for textView

fun TextView.setStrikeThrough() {
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}


fun TextView.removeStrikeThrough(){
    paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}

// Paints flags are like series of bits, put one bit to 1 using logic operation
