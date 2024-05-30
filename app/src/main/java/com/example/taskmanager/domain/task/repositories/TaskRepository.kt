package com.example.taskmanager.domain.task.repositories

import com.example.taskmanager.core.utils.errors.Failure
import com.example.taskmanager.core.utils.types.Either
import com.example.taskmanager.core.utils.types.Option
import com.example.taskmanager.domain.task.entities.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Either<Failure, Flow<List<Task>>>
    suspend fun addTask(task: Task): Option<Failure>
    suspend fun updateTaskStatus(task: Task): Option<Failure>
    suspend fun deleteTask(task: Task): Option<Failure>
}