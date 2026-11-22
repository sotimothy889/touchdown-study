package com.ts.touchdownstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ts.touchdownstudy.ui.screens.LeaderboardScreen
import com.ts.touchdownstudy.ui.screens.TimerScreen
import com.ts.touchdownstudy.ui.theme.TouchdownStudyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TouchdownStudyTheme {

                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "timerscreen",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("timerscreen") {
                            TimerScreen(
                                onLeaderboardClick = { navController.navigate("leaderboard") }
                            )
                        }
                        composable("leaderboard") {
                            LeaderboardScreen(
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
