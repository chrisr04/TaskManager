package com.example.taskmanager.ui.screens.tasks.viewmodel

import com.example.taskmanager.domain.task.entities.Task

sealed class TasksUiState(open val data: TaskUiData) {
    data class TasksInitialState(override val data: TaskUiData) : TasksUiState(data)
    data class TasksLoadedState(override val data: TaskUiData) : TasksUiState(data)

    data class LoadTaskFailureState(
        override val data: TaskUiData,
        val message: String
    ) : TasksUiState(data)

    data class OpenTaskDialogState(override val data: TaskUiData) : TasksUiState(data)
    data class CloseTaskDialogState(override val data: TaskUiData) : TasksUiState(data)
}

data class TaskUiData(
    val showDialog: Boolean = false,
    val tasks: List<Task> = emptyList(),
)