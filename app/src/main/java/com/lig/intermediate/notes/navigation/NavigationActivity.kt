package com.lig.intermediate.notes.navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lig.intermediate.notes.R
import com.lig.intermediate.notes.create.CreateActivity
import com.lig.intermediate.notes.ui.notes.NotesFragment
import com.lig.intermediate.notes.ui.task.TaskFragment

/* MVVM architecture -> very useful for unit testing
*  view: fragment+view  viewModel: Business Logic, livedata     Model:Injected
*  liveData: view observing it will be notified when changed
*  fragment observe livedata from viewmodel and forwards it to view
*  The Model: Manipulate and retrive data
*
* */

class NavigationActivity : AppCompatActivity(), TaskFragment.TouchActionDelegate,
    NotesFragment.NoteActionDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_task,
            R.id.navigation_notes
        ).build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)

    }

    private fun goToCreateActivity(fragmentValue: String) {
        // use apply to put extra is very quick
        startActivity(Intent(this, CreateActivity::class.java).apply {
            putExtra(FRAGEMENT_TYPE_KEY, fragmentValue)
        })
    }

    override fun onAddButtonClicked(value: String) {
        goToCreateActivity(value)
    }

    companion object {
        const val FRAGEMENT_TYPE_KEY = "f_t_k"
        const val FRAGEMENT_VALUE_NOTE = "f_v_n"
        const val FRAGEMENT_VALUE_TASK = "f_v_t"
    }

}


/* java call
    Tag tag = new Tag("High priority", R.color.colorAccent);
    Task task = new Task("Get Groceries");
    String s = task.getTitle();
    */