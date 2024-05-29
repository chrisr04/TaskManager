package com.example.taskmanager.domain.task.repositories

import com.example.taskmanager.domain.task.entities.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>
    suspend fun addTask(task: Task)
    suspend fun updateTaskStatus(task: Task)
    suspend fun deleteTask(task:Task)
}