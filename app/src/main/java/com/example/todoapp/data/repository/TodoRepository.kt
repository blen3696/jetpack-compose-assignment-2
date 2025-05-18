package com.example.todoapp.data.repository

import com.example.todoapp.data.local.TodoDao
import com.example.todoapp.data.model.Todo
import com.example.todoapp.data.network.ApiService

class TodoRepository(
    private val dao: TodoDao,
    private val api: ApiService
) {
    suspend fun getTodos(): List<Todo> {
        return try {
            val todos = api.getTodos()
            dao.insertAll(todos)
            todos
        } catch (e: Exception) {
            dao.getAllTodos()
        }
    }

    suspend fun getTodoById(id: Int): Todo {
        return dao.getTodoById(id)
    }
}


