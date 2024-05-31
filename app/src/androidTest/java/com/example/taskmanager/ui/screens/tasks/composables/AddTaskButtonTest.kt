package com.example.taskmanager.ui.screens.tasks.composables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.example.taskmanager.ui.theme.TaskManagerTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class AddTaskButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenButtonIsClicked() {
        var isClicked = false

        composeTestRule.setContent {
            TaskManagerTheme {
                AddTaskButton {
                    isClicked = true
                }
            }
        }

        val buttonNode = composeTestRule.onNodeWithContentDescription("Add task icon")

        buttonNode.assertIsDisplayed().performClick()

        assertEquals(true, isClicked)
    }
}