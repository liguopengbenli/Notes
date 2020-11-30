package com.lig.intermediate.notes.foundations

import android.text.Editable
import android.text.TextWatcher

// When you implement a interface but only some method, use a abstract class to extent the interface
// in order to hide others method which don't have operations

open class StateChangeTextWatcher: TextWatcher {

     var previousValue: String? = null

     override fun afterTextChanged(s: Editable?) {
         previousValue = s?.toString()
     }

     override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // no operation
     }

     override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
         // no operation
     }

     /*override fun afterTextChanged(s: Editable?) {
        implemented
     }*/

 }