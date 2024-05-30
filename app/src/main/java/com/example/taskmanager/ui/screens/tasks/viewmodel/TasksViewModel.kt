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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    getTasksUseCase: GetTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val changeTaskStatusUseCase: ChangeTaskStatusUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<TasksUiState>(TasksInitialState(TaskUiData()))
    val uiState = _uiState.asStateFlow()

    init {
        val failureOrTasksFlow = getTasksUseCase()
        failureOrTasksFlow.fold(
            onLeft = { failure ->
                _uiState.value = LoadTaskFailureState(
                    data = uiState.value.data,
                    message = failure.message,
                )
            },
            onRight = { tasksFlow ->
                viewModelScope.launch {
                    tasksFlow.catch { error ->
                        _uiState.value = LoadTaskFailureState(
                            data = uiState.value.data,
                            message = error.message.orEmpty(),
                        )
                    }.collect {
                        _uiState.value = TasksLoadedState(
                            uiState.value.data.copy(
                                tasks = it
                            )
                        )
                    }
                }
            }
        )
    }

    fun onInitScreen(keepData: Boolean = true) {
        _uiState.value = TasksInitialState(
            if (keepData) uiState.value.data else TaskUiData()
        )
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
            val failureOrCreated = addTaskUseCase(task)
            failureOrCreated.fold(
                onNone = {},
                onSome = { failure ->
                    _uiState.value = LoadTaskFailureState(
                        data = uiState.value.data,
                        message = failure.message,
                    )
                }
            )
        }
    }

    fun onChangeTaskStatus(task: Task) {
        viewModelScope.launch {
            val failureOrUpdated = changeTaskStatusUseCase(task)
            failureOrUpdated.fold(
                onNone = {},
                onSome = { failure ->
                    _uiState.value = LoadTaskFailureState(
                        data = uiState.value.data,
                        message = failure.message,
                    )
                }
            )
        }
    }

    fun onDeleteTask(task: Task) {
        viewModelScope.launch {
            val failureOrDeleted = deleteTaskUseCase(task)
            failureOrDeleted.fold(
                onNone = {},
                onSome = { failure ->
                    _uiState.value = LoadTaskFailureState(
                        data = uiState.value.data,
                        message = failure.message,
                    )
                }
            )
        }
    }
}