package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import com.example.todoapp.data.local.TodoDatabase
import com.example.todoapp.data.network.ApiService
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.viewmodel.TodoViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = TodoDatabase.getDatabase(applicationContext)
        val api = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val repository = TodoRepository(db.todoDao(), api)
        val viewModel = TodoViewModel(repository)

        setContent {
            MaterialTheme(
                colors = lightColors(primary = androidx.compose.ui.graphics.Color(0xFF66BB6A))
            ) {
                AppNavigation(viewModel)
            }
        }
    }
}
