package com.lig.intermediate.notes.database

import androidx.room.*
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.TaskEntity
import com.lig.intermediate.notes.models.Todo

@Dao
interface TaskDAO {
    @Insert
    fun addTask(task: TaskEntity)

    @Insert
    fun addTodo(todo: Todo)

    @Update
    fun updateTask(task: TaskEntity)

    @Update
    fun updateTodo(todo: Todo)

    @Delete
    fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun retrieveTask(): MutableList<Task>

}