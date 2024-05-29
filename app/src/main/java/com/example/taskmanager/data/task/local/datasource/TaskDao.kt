package com.example.taskmanager.data.task.local.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskmanager.data.task.local.model.TaskRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<TaskRoomModel>>

    @Insert
    suspend fun addTask(task:TaskRoomModel)

    @Update
    suspend fun updateTask(task:TaskRoomModel)

    @Delete
    suspend fun deleteTask(task: TaskRoomModel)
}