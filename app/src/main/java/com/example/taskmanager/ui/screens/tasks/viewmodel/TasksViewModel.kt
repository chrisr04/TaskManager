package com.example.taskmanager.ui.screens.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.domain.task.entities.Task
import com.example.taskmanager.domain.task.usescases.AddTaskUseCase
import com.example.taskmanager.domain.task.usescases.ChangeTaskStatusUseCase
import com.example.taskmanager.domain.task.usescases.DeleteTaskUseCase
import com.example.taskmanager.domain.task.usescases.GetTasksUseCase
import com.example.taskmanager.ui.screens.tasks.viewmodel.TasksUiState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val changeTaskStatusUseCase: ChangeTaskStatusUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<TasksUiState>(TasksInitialState(TaskUiData()))
    val uiState = _uiState.asStateFlow()

    fun onInitScreen(keepData: Boolean = true) {
        viewModelScope.launch {
            _uiState.value = TasksInitialState(
                if (keepData) uiState.value.data else TaskUiData()
            )

            val tasksFlow = getTasksUseCase()
            tasksFlow.collect {
                _uiState.value = LoadTasksState(
                    uiState.value.data.copy(tasks = it)
                )
            }
        }
    }

    fun onOpenTaskDialog() {
        _uiState.value = OpenTaskDialogState(
            uiState.value.data.copy(
                showDialog = true
            )
        )
    }

    fun onCloseTaskDialog() {
        _uiState.value = CloseTaskDialogState(
            uiState.value.data.copy(
                showDialog = false
            )
        )
    }

    fun onCreateTask(task: Task) {
        viewModelScope.launch {
            _uiState.value = CloseTaskDialogState(
                uiState.value.data.copy(
                    showDialog = false
                )
            )
            addTaskUseCase(task)
        }
    }

    fun onChangeTaskStatus(task: Task) {
        viewModelScope.launch {
            changeTaskStatusUseCase(task)
        }
    }

    fun onDeleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }
}