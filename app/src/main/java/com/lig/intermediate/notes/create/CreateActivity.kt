package com.lig.intermediate.notes.create

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        supportActionBar?.title = ""

        intent.getStringExtra(NavigationActivity.FRAGEMENT_TYPE_KEY).run {
            textView.text = if (this == NavigationActivity.FRAGEMENT_VALUE_TASK) {
                "This is a task"
            } else if (this == NavigationActivity.FRAGEMENT_VALUE_NOTE) {
                "This is Note"
            } else {
                "Something went wrong"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)  // inflate custom menu to menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.saveItem -> Toast.makeText(this, "saveClicked", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }

}