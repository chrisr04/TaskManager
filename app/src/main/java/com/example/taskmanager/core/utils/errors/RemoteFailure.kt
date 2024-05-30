package com.example.taskmanager.core.utils.errors

data class RemoteFailure(
    override val message: String,
    val statusCode: Int?
) : Failure(message) {
    override fun toString(): String {
        return "Remote Failure $statusCode: $message"
    }
}