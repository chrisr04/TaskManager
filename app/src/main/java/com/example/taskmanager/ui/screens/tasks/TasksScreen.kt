package com.example.taskmanager.ui.screens.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.taskmanager.ui.screens.tasks.composables.AddTaskButton
import com.example.taskmanager.ui.screens.tasks.composables.CreateTaskDialog
import com.example.taskmanager.ui.screens.tasks.composables.TaskList
import com.example.taskmanager.ui.screens.tasks.viewmodel.TasksViewModel

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {
    val uiState by tasksViewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            AddTaskButton { tasksViewModel.onOpenTaskDialog() }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            TaskList(uiState.data.tasks, tasksViewModel)
            CreateTaskDialog(
                showDialog = uiState.data.showDialog,
                onDismiss = { tasksViewModel.onCloseTaskDialog() },
                onAddTask = { tasksViewModel.onCreateTask(it) },
            )
        }
    }
}
