package com.ts.touchdownstudy.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TimerDisplay(
    secondsRemaining: Int,
    isRunning: Boolean,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val minutes = secondsRemaining / 60
    val seconds = secondsRemaining % 60
    val formattedTime = "%02d:%02d".format(minutes, seconds)

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
                Button(onClick = onPauseClick) {
                    Text("Pause")
                }
            } else {
                Button(
                    onClick = onStartClick,
                    enabled = secondsRemaining > 0
                ) {
                    Text("Start")
                }
            }

            OutlinedButton(onClick = onResetClick) {
                Text("Reset")
            }
        }
    }
}
