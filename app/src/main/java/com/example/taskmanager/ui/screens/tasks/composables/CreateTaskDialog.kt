package com.example.taskmanager.ui.screens.tasks.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.taskmanager.domain.task.entities.Task
import com.example.taskmanager.ui.screens.tasks.TasksScreenTags

@Composable
fun CreateTaskDialog(
    showDialog: Boolean,
    onCreateTask: (Task) -> Unit,
    onDismiss: () -> Unit
) {
    if (!showDialog) return

    var taskName by rememberSaveable { mutableStateOf("") }
    var taskDescription by rememberSaveable { mutableStateOf("") }
    val isButtonEnabled = taskName.isNotEmpty()

    Dialog(onDismissRequest = onDismiss) {
        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .testTag(TasksScreenTags.DIALOG)

        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable(onClick = onDismiss),
                imageVector = Icons.Filled.Close,
                contentDescription = "Close create task dialog"
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Nueva tarea",
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = taskName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag(TasksScreenTags.DIALOG_NAME_INPUT),
                textStyle = TextStyle(lineHeight = 1.sp),
                singleLine = true,
                maxLines = 1,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                ),
                placeholder = {
                    Text(
                        text = "Escribe el nombre de la tarea",
                        style = TextStyle(lineHeight = 1.sp),
                    )
                },
                onValueChange = { taskName = it },
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = taskDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .testTag(TasksScreenTags.DIALOG_DESCRIPTION_INPUT),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                ),
                placeholder = {
                    Text(
                        text = "Escribe una descripción breve",
                        style = TextStyle(lineHeight = 1.sp),
                    )
                },
                onValueChange = { taskDescription = it },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(TasksScreenTags.DIALOG_ADD_BUTTON),
                contentPadding = PaddingValues(12.dp),
                enabled = isButtonEnabled,
                onClick = {
                    val newTask = Task(
                        id = System.currentTimeMillis(),
                        name = taskName,
                        description = taskDescription,
                        completed = false,
                    )
                    onCreateTask(newTask)
                }
            ) {
                Text(text = "Agregar")
            }
        }
    }
}