package com.lig.intermediate.notes.models

// kotlin auto generate setter and gettter
// data class auto generate toString, copie...
// predefine value to set defaut value // if using in java we have to add @JvmOverloads constructor

data class Task @JvmOverloads constructor(
    var title:String,
    val todos: MutableList<Todo> = mutableListOf(), // create defaut empty list
    val tag: Tag? = null
)

data class Todo(
    var description: String,
    var isComplete: Boolean = false
)

data class Note(
    var description: String,
    var tag: Tag? = null
)

data class Tag(
    val name:String,
    val colorResId: Int
)