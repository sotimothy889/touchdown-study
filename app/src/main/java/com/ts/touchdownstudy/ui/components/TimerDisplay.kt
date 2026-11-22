package com.ts.touchdownstudy.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TimerDisplay(
    elapsedSeconds: Int,
    isRunning: Boolean,
    onStartClick: () -> Unit,
    onEndSessionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val hours = elapsedSeconds / 3600
    val minutes = (elapsedSeconds % 3600) / 60
    val seconds = elapsedSeconds % 60
    val formattedTime = if (hours > 0) {
        "%d:%02d:%02d".format(hours, minutes, seconds)
    } else {
        "%02d:%02d".format(minutes, seconds)
    }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = formattedTime,
            style = MaterialTheme.typography.displayLarge
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            if (isRunning) {
                Button(onClick = onEndSessionClick) {
                    Text("End Session")
                }
            } else {
                Button(onClick = onStartClick) {
                    Text("Start")
                }
                if (elapsedSeconds > 0) {
                    Button(onClick = onEndSessionClick) {
                        Text("End Session")
                    }
                }
            }
        }
    }
}
