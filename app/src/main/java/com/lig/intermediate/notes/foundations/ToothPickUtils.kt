package com.lig.intermediate.notes.foundations

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
        bind(INoteModel::class.java).toInstance(NoteLocalModel())
        bind(ITaskModel::class.java).toInstance(TaskLocalModel())
    }
}