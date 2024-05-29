package com.example.taskmanager.data.task.local.datasource


import com.example.taskmanager.data.task.local.model.TaskRoomModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskLocalDataSource @Inject constructor(
    private val taskDao: TaskDao,
    private val ioDispatcher: CoroutineDispatcher,
) {
    fun getTasks(): Flow<List<TaskRoomModel>> = taskDao.getTasks()

    suspend fun addTask(task: TaskRoomModel) = withContext(ioDispatcher) {
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: TaskRoomModel) = withContext(ioDispatcher) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskRoomModel) = withContext(ioDispatcher) {
        taskDao.deleteTask(task)
    }
}