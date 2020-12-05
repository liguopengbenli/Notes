package com.lig.intermediate.notes

import com.lig.intermediate.notes.models.Task
import com.lig.intermediate.notes.models.Todo
import org.junit.Before
import org.junit.Test

class TaskTest {

    lateinit var testTask: Task

    private fun getFakeTask(): Task = Task(
        title = "Testing Task",
        todos = getFakeTodos()
    )

    private fun getFakeTodos(): MutableList<Todo> = mutableListOf(
        Todo(description = "todo one", isComplete = true),
        Todo(description = "todo two"),
        Todo(description = "todo three")
    )

    @Before
    fun setupBeforeTest(){
        testTask = getFakeTask()
    }

    @Test
    fun testIsIncompleteOnInit(){
        assert(!testTask.isComplete())   // pass when return true
    }

    @Test
    fun taskIsCompleteAfterAllTodosChecked(){
        for (todo in testTask.todos) {
            todo.isComplete = true
        }
        assert(testTask.isComplete())
    }

}