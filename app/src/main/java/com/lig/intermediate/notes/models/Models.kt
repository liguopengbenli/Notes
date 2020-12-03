package com.lig.intermediate.notes.models

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.util.*

// kotlin auto generate setter and gettter
// data class auto generate toString, copie...
// predefine value to set defaut value // if using in java we have to add @JvmOverloads constructor


class Task @JvmOverloads constructor(
    title: String,
    @Relation(
        parentColumn = "uid",
        entityColumn = "taskId",
        entity = Todo::class
    )
    val todos: MutableList<Todo> = mutableListOf(), // create default empty list
    tag: Tag? = null
) : TaskEntity(title = title, tag = tag)
{
    init {
        todos.forEach {
            it.taskId = uid
        }
    }
}



@Entity(tableName = "tasks")
open class TaskEntity(  // create open class because of todos list
    @PrimaryKey
    val uid: Long = UUID.randomUUID().leastSignificantBits,
    @ColumnInfo
    var title: String,
    @Embedded
    val tag: Tag? = null
)

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var uid:Int = 0,
    @ForeignKey(                  //for join
        parentColumns = ["uid"],
        childColumns = ["taskId"],
        entity = TaskEntity::class,
        onDelete = CASCADE   // it will delete to do when the task is deleted
    )
    var taskId:Long? = null,  // = uid of TaskEntity for making join
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var isComplete: Boolean = false
)

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) // auto increase value when assign to db
    var uid: Int = 0,
    @ColumnInfo
    var description: String,
    @Embedded
    var tag: Tag? = null
)


@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "colour_resource_id")
    val colourResId: Int
)