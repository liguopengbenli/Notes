package com.lig.intermediate.notes.foundations

import com.lig.intermediate.notes.create.StateModel
import com.lig.intermediate.notes.ui.notes.INoteModel
import com.lig.intermediate.notes.ui.notes.NoteLocalModel
import com.lig.intermediate.notes.ui.task.ITaskModel
import com.lig.intermediate.notes.ui.task.TaskLocalModel
import toothpick.Toothpick
import toothpick.config.Module

object ApplicationScope{ // object in kotlin is a singleton class

    val scope = Toothpick.openScope(this).apply {
        installModules(ApplicationModule)
    }
}

object ApplicationModule: Module(){
    init {
        bind(INoteModel::class.java).toInstance(NoteLocalModel()) // call constructor singleton
        bind(ITaskModel::class.java).toInstance(TaskLocalModel())
    }
}

// Alternative way
object CreateActivityScope{
    val scope = Toothpick.openScope(this).apply {
        installModules(CreateActivityModule)
    }
}

object CreateActivityModule: Module(){
    init {
        bind(INoteModel::class.java).to(NoteLocalModel::class.java)
        bind(ITaskModel::class.java).to(TaskLocalModel::class.java)  // Here we don't use constructor but new dependency injection method
        bind(StateModel::class.java).toInstance(StateModel()) // call singleton
    }
}