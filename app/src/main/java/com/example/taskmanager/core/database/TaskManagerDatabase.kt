package com.example.taskmanager.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanager.data.task.local.datasource.TaskDao
import com.example.taskmanager.data.task.local.model.TaskRoomModel

@Database(entities = [TaskRoomModel::class], version = 1)
abstract class TaskManagerDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}