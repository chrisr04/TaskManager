package com.example.taskmanager.ui.screens.tasks.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AddTaskButton(onClick: () -> Unit) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add task icon"
        )
    }
}