package com.example.taskmanager.data.task

import com.example.taskmanager.data.task.local.datasource.TaskDao
import com.example.taskmanager.data.task.local.datasource.TaskLocalDataSource
import com.example.taskmanager.data.task.repository.TaskRepositoryImpl
import com.example.taskmanager.domain.task.repositories.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TasksModule {

    @Provides
    @Singleton
    fun provideTasksRoomDataSource(taskDao: TaskDao): TaskLocalDataSource =
        TaskLocalDataSource(taskDao, Dispatchers.IO)

    @Provides
    @Singleton
    fun provideTaskRepository(tasksLocalDataSource: TaskLocalDataSource): TaskRepository =
        TaskRepositoryImpl(tasksLocalDataSource)
}