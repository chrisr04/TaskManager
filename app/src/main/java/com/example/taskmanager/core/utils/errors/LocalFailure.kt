package com.example.taskmanager.core.utils.errors

data class LocalFailure(
    override val message: String
) : Failure(message)

