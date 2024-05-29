package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.ui.navigation.TasksRoute
import com.example.taskmanager.ui.screens.tasks.TasksScreen
import com.example.taskmanager.ui.screens.tasks.viewmodel.TasksViewModel
import com.example.taskmanager.ui.theme.TaskManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tasksViewModel: TasksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TaskManagerTheme(dynamicColor = false) {
                NavHost(
                    navController = navController,
                    startDestination = TasksRoute
                ) {
                    composable<TasksRoute> {
                        tasksViewModel.onInitScreen()
                        TasksScreen(tasksViewModel)
                    }
                }
            }
        }
    }
}