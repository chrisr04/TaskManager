package com.example.taskmanager.domain.task.entities

data class Task(
    val id: Long,
    val name: String,
    val description: String,
    val completed: Boolean
)