package com.example.taskmanager.domain.task.usescases

import com.example.taskmanager.domain.task.entities.Task
import com.example.taskmanager.domain.task.repositories.TaskRepository
import javax.inject.Inject

class ChangeTaskStatusUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: Task) = taskRepository.updateTaskStatus(task)
}