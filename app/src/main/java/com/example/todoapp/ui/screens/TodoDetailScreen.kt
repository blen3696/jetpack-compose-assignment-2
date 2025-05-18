package com.example.todoapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.viewmodel.TodoViewModel

@Composable
fun TodoDetailScreen(viewModel: TodoViewModel, id: Int, onBack: () -> Unit) {
    LaunchedEffect(id) {
        viewModel.selectTodo(id)
    }

    val todo by viewModel.selectedTodo.collectAsState()

    todo?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Title: ${it.title}", style = MaterialTheme.typography.h5)
            Text("Completed: ${it.completed}", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onBack) {
                Text("Back")
            }
        }
    }
}
