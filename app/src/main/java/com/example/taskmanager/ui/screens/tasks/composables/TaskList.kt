package com.example.taskmanager.ui.screens.tasks.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.domain.task.entities.Task
import com.example.taskmanager.ui.screens.tasks.viewmodel.TasksViewModel

@Composable
fun TaskList(tasks: List<Task>, tasksViewModel: TasksViewModel) {
    LazyColumn(Modifier.fillMaxSize()) {
        item { TaskListHeader() }
        items(tasks, key = { it.id }) { task ->
            TaskItem(
                task = task,
                onCheckedChange = {
                    val newTask = task.copy(completed = !task.completed)
                    tasksViewModel.onChangeTaskStatus(newTask)
                },
                onClickDelete = { tasksViewModel.onDeleteTask(task) }
            )
            TaskItemSeparator()
        }
    }
}

@Composable
fun TaskListHeader() {
    Text(
        "Mis Tareas",
        fontSize = 24.sp,
        fontWeight = FontWeight.W700,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
    )
}

@Composable
fun TaskItem(task: Task, onCheckedChange: (Boolean) -> Unit, onClickDelete: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = task.completed,
            onCheckedChange = onCheckedChange
        )
        Column(Modifier.weight(1f)) {
            Text(
                task.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600
            )
            if (task.description.isNotEmpty())
                Text(
                    task.description,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    lineHeight = 20.sp,
                    fontSize = 12.sp,
                )
        }
        IconButton(onClick = onClickDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete task icon"
            )
        }
    }
}

@Composable
fun TaskItemSeparator() {
    HorizontalDivider(
        thickness = 0.8.dp,
        color = MaterialTheme.colorScheme.outline,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}