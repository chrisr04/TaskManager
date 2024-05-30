package com.example.taskmanager.domain.task.usescases

import com.example.taskmanager.core.utils.errors.Failure
import com.example.taskmanager.core.utils.types.Option
import com.example.taskmanager.domain.task.entities.Task
import com.example.taskmanager.domain.task.repositories.TaskRepository
import javax.inject.Inject

class ChangeTaskStatusUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: Task): Option<Failure> =
        taskRepository.updateTaskStatus(task)
}