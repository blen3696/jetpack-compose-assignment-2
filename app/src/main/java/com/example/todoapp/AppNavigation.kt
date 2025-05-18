package com.example.todoapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.todoapp.ui.screens.TodoDetailScreen
import com.example.todoapp.ui.screens.TodoListScreen
import com.example.todoapp.viewmodel.TodoViewModel

@Composable
fun AppNavigation(viewModel: TodoViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {
        composable("list") {
            TodoListScreen(viewModel) {
                navController.navigate("detail/$it")
            }
        }
        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            TodoDetailScreen(viewModel, id) {
                navController.popBackStack()
            }
        }
    }
}
