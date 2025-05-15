package org.example.project.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BaseScreenContainer(hasBottomPadding: Boolean = true, content: @Composable () -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 30.dp)
        .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp,)
            .padding(bottom = if (hasBottomPadding) 40.dp else 0.dp, top = 30.dp)
        ) {
            content()
        }
    }
}