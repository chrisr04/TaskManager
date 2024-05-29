package com.example.taskmanager.domain.task.entities

import com.example.taskmanager.data.task.local.model.TaskRoomModel

data class Task(
    val id: Long,
    val name: String,
    val description: String,
    val completed: Boolean
)

fun Task.toLocalData() = TaskRoomModel(
    id = id,
    name = name,
    description = description,
    completed = completed,
)