package com.lig.intermediate.notes.create

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        intent.getStringExtra(NavigationActivity.FRAGEMENT_TYPE_KEY).run {
            textView.text = if(this == NavigationActivity.FRAGEMENT_VALUE_TASK){
                "This is a task"
            } else if(this == NavigationActivity.FRAGEMENT_VALUE_NOTE)
            {
                "This is Note"
            }else {
                "Something went wrong"
            }
        }

    }
}