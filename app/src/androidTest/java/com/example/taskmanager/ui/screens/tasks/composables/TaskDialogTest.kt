package com.example.taskmanager.ui.screens.tasks.composables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import com.example.taskmanager.domain.task.entities.Task
import com.example.taskmanager.ui.screens.tasks.TasksScreenTags
import com.example.taskmanager.ui.theme.TaskManagerTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class TaskDialogTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenShowDialogIsTrue_thenDialogIsVisible() {
        composeTestRule.setContent {
            TaskManagerTheme {
                TaskDialog(
                    showDialog = true,
                    onCreateTask = {},
                    onDismiss = {},
                )
            }
        }

        composeTestRule.onNodeWithTag(TasksScreenTags.DIALOG).assertIsDisplayed()
    }

    @Test
    fun whenShowDialogIsFalse_thenDialogIsNotVisible() {
        composeTestRule.setContent {
            TaskManagerTheme {
                TaskDialog(
                    showDialog = false,
                    onCreateTask = {},
                    onDismiss = {},
                )
            }
        }

        composeTestRule.onNodeWithTag(TasksScreenTags.DIALOG).assertDoesNotExist()
    }

    @Test
    fun whenNameInputIsEmpty_thenAddButtonIsDisabled() {
        composeTestRule.setContent {
            TaskManagerTheme {
                TaskDialog(
                    showDialog = true,
                    onCreateTask = {},
                    onDismiss = {},
                )
            }
        }

        val nameInputNode = composeTestRule.onNodeWithTag(TasksScreenTags.DIALOG_NAME_INPUT)
        val addButtonNode = composeTestRule.onNodeWithTag(TasksScreenTags.DIALOG_ADD_BUTTON)
        nameInputNode.performTextReplacement("")
        addButtonNode.assertIsNotEnabled()
    }

    @Test
    fun whenNameInputIsNotEmpty_thenAddButtonIsEnabled() {
        composeTestRule.setContent {
            TaskManagerTheme {
                TaskDialog(
                    showDialog = true,
                    onCreateTask = {},
                    onDismiss = {},
                )
            }
        }

        val nameInputNode = composeTestRule.onNodeWithTag(TasksScreenTags.DIALOG_NAME_INPUT)
        val addButtonNode = composeTestRule.onNodeWithTag(TasksScreenTags.DIALOG_ADD_BUTTON)
        nameInputNode.performTextReplacement("My test task")
        addButtonNode.assertIsEnabled()
    }

    @Test
    fun whenAddButtonIsClicked_thenLaunchOnCreateTask() {
        lateinit var createdTask: Task

        composeTestRule.setContent {
            TaskManagerTheme {
                TaskDialog(
                    showDialog = true,
                    onCreateTask = { createdTask = it },
                    onDismiss = {},
                )
            }
        }

        val nameInputNode = composeTestRule.onNodeWithTag(TasksScreenTags.DIALOG_NAME_INPUT)
        val descriptionInputNode = composeTestRule.onNodeWithTag(TasksScreenTags.DIALOG_DESCRIPTION_INPUT)
        val addButtonNode = composeTestRule.onNodeWithTag(TasksScreenTags.DIALOG_ADD_BUTTON)

        nameInputNode.performTextInput("My test task")
        descriptionInputNode.performTextInput("My description test")
        addButtonNode.assertIsEnabled()
        addButtonNode.performClick()

        assertEquals(createdTask.name, "My test task")
        assertEquals(createdTask.description, "My description test")
        assertEquals(createdTask.completed, false)
    }
}