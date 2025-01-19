package com.example.taskmanager.data.task.repository

import com.example.taskmanager.core.utils.errors.Failure
import com.example.taskmanager.core.utils.errors.LocalFailure
import com.example.taskmanager.core.utils.types.Either
import com.example.taskmanager.core.utils.types.Option
import com.example.taskmanager.data.task.local.datasource.TaskLocalDataSource
import com.example.taskmanager.data.task.local.model.toDomain
import com.example.taskmanager.data.task.local.model.toLocal
import com.example.taskmanager.domain.task.entities.Task
import com.example.taskmanager.domain.task.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val localDataSource: TaskLocalDataSource
) : TaskRepository {

    override fun getTasks(): Either<Failure, Flow<List<Task>>> = try {
        val tasksFlow = localDataSource.getTasks()
        Either.Right(
            tasksFlow.map { tasks -> tasks.map { it.toDomain() } }
        )
    } catch (error: Throwable) {
        Either.Left(
            LocalFailure(
                message = error.message.orEmpty(),
            )
        )
    }


    override suspend fun addTask(task: Task): Option<Failure> = try {
        localDataSource.addTask(task.toLocal())
        Option.None
    } catch (error: Throwable) {
        Option.Some(
            LocalFailure(
                message = error.message.orEmpty(),
            )
        )
    }

    override suspend fun updateTaskStatus(task: Task): Option<Failure> = try {
        localDataSource.updateTask(task.toLocal())
        Option.None
    } catch (error: Throwable) {
        Option.Some(
            LocalFailure(
                message = error.message.orEmpty(),
            )
        )
    }

    override suspend fun deleteTask(task: Task): Option<Failure> = try {
        localDataSource.deleteTask(task.toLocal())
        Option.None
    } catch (error: Throwable) {
        Option.Some(
            LocalFailure(
                message = error.message.orEmpty(),
            )
        )
    }
}