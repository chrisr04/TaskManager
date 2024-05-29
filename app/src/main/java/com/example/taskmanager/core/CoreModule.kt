package com.example.taskmanager.core

import android.content.Context
import androidx.room.Room
import com.example.taskmanager.core.database.TaskManagerDatabase
import com.example.taskmanager.data.task.local.datasource.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    fun provideTaskManagerDatabase(@ApplicationContext context: Context): TaskManagerDatabase =
        Room.databaseBuilder(
            context,
            klass = TaskManagerDatabase::class.java,
            name = "taskManagerDB"
        ).build()


    @Provides
    fun provideTaskDao(taskManagerDatabase: TaskManagerDatabase): TaskDao =
        taskManagerDatabase.taskDao()
}