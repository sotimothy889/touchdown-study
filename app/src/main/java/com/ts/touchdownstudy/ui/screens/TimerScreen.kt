package com.ts.touchdownstudy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ts.touchdownstudy.R
import com.ts.touchdownstudy.ui.components.TimerDisplay
import com.ts.touchdownstudy.viewmodel.TimerViewModel

@Composable
fun TimerScreen(
    onLeaderboardClick: () -> Unit,
    viewModel: TimerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_PAUSE) {
        viewModel.onAppBackgrounded()
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.onAppForegrounded()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Top row: Coins and Leaderboard button
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Coins: ${uiState.coins}",
                style = MaterialTheme.typography.titleMedium
            )
            Button(onClick = onLeaderboardClick) {
                Text("Leaderboard")
            }
        }

        // Center content: Sprite image and timer display
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (uiState.spriteImageUrl.isNotBlank()) {
                AsyncImage(
                    model = uiState.spriteImageUrl,
                    contentDescription = "Main Sprite Image",
                    modifier = Modifier.size(180.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Placeholder Decoration Image",
                    modifier = Modifier.size(180.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            TimerDisplay(
                elapsedSeconds = uiState.elapsedSeconds,
                isRunning = uiState.isRunning,
                onStartClick = viewModel::startOrResume,
                onEndSessionClick = viewModel::endSession
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    TimerScreen(onLeaderboardClick = {})
}
