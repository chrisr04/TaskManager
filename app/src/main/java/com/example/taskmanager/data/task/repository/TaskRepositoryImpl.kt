package com.example.taskmanager.data.task.repository

import com.example.taskmanager.data.task.local.datasource.TaskLocalDataSource
import com.example.taskmanager.data.task.local.model.toDomain
import com.example.taskmanager.domain.task.entities.Task
import com.example.taskmanager.domain.task.entities.toLocalData
import com.example.taskmanager.domain.task.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val localDataSource: TaskLocalDataSource
) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> {
        val tasksFlow = localDataSource.getTasks()
        return tasksFlow.map { tasks -> tasks.map { it.toDomain() } }
    }

    override suspend fun addTask(task: Task) {
        localDataSource.addTask(task.toLocalData())
    }

    override suspend fun updateTaskStatus(task: Task) {
        localDataSource.updateTask(task.toLocalData())
    }

    override suspend fun deleteTask(task: Task) {
        localDataSource.deleteTask(task.toLocalData())
    }
}