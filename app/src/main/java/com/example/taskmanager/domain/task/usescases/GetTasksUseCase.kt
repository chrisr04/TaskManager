package com.example.taskmanager.domain.task.usescases

import com.example.taskmanager.core.utils.errors.Failure
import com.example.taskmanager.core.utils.types.Either
import com.example.taskmanager.domain.task.entities.Task
import com.example.taskmanager.domain.task.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(): Either<Failure, Flow<List<Task>>> = taskRepository.getTasks()
}