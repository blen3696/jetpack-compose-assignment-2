package com.example.todoapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todoapp.viewmodel.TodoViewModel

@Composable
fun TodoListScreen(viewModel: TodoViewModel, onItemClick: (Int) -> Unit) {
    val todos by viewModel.todos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchTodos()
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(todos) { todo ->
            Card(
                backgroundColor = if (todo.completed) Color(0xFFA5D6A7) else Color(0xFFE8F5E9),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onItemClick(todo.id) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(todo.title, style = MaterialTheme.typography.h6)
                    Text("Completed: ${todo.completed}")
                }
            }
        }
    }
}


