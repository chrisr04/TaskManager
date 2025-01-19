package com.example.taskmanager.data.task.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskmanager.domain.task.entities.Task

@Entity(tableName = "task")
data class TaskRoomModel(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val completed: Boolean
)

fun TaskRoomModel.toDomain() = Task(
    id = id,
    name = name,
    description = description,
    completed = completed,
)

fun Task.toLocal() = TaskRoomModel(
    id = id,
    name = name,
    description = description,
    completed = completed,
)