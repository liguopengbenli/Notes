package com.lig.intermediate.notes.database

import androidx.room.*
import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.TaskEntity

@Dao
interface TaskDAO {
    @Insert
    fun addTask(task: TaskEntity)

    @Update
    fun updateTask(task: TaskEntity)

    @Delete
    fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun retrieveTask(): MutableList<Task>

}