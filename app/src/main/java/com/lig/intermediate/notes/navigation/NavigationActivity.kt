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
import com.lig.intermediate.notes.ui.task.TaskFragment

class NavigationActivity : AppCompatActivity(), TaskFragment.TouchActionDelegate {
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

    private fun goToCreateActivity(){
        startActivity(Intent(this, CreateActivity::class.java))
    }

    override fun onAddButtonClicked() {
        goToCreateActivity()
    }

}




/* java call
    Tag tag = new Tag("High priority", R.color.colorAccent);
    Task task = new Task("Get Groceries");
    String s = task.getTitle();
    */