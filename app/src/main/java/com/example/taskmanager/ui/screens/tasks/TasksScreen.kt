package com.example.taskmanager.ui.screens.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.taskmanager.ui.screens.tasks.composables.AddTaskButton
import com.example.taskmanager.ui.screens.tasks.composables.TaskDialog
import com.example.taskmanager.ui.screens.tasks.composables.TaskList
import com.example.taskmanager.ui.screens.tasks.viewmodel.TasksUiState.*
import com.example.taskmanager.ui.screens.tasks.viewmodel.TasksViewModel

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {
    val uiState by tasksViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        tasksViewModel.uiState.collect { uiState ->
            when (uiState) {
                is LoadTaskFailureState -> {
                    snackbarHostState.showSnackbar(uiState.message)
                }

                else -> {}
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            AddTaskButton { tasksViewModel.onOpenTaskDialog() }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            TaskList(
                tasks = uiState.data.tasks,
                onDeleteTask = { tasksViewModel.onDeleteTask(it) },
                onChangeTaskStatus = { task, completed ->
                    val changedTask = task.copy(completed = completed)
                    tasksViewModel.onChangeTaskStatus(changedTask)
                },
            )
            TaskDialog(
                showDialog = uiState.data.showDialog,
                onDismiss = { tasksViewModel.onCloseTaskDialog() },
                onCreateTask = { tasksViewModel.onCreateTask(it) }
            )
        }
    }
}
