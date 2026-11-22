package com.ts.touchdownstudy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ts.touchdownstudy.data.repository.DecorationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class TimerUiState(
    val elapsedSeconds: Int = 0,
    val isRunning: Boolean = false,
    val coins: Int = 0,
    val spriteImageUrl: String = ""
)

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val decorationRepository: DecorationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TimerUiState())
    val uiState: StateFlow<TimerUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    // Tracks if the timer should auto-resume when returning to the app
    private var wasRunningBeforeBackground = false

    init {
        loadCoins()
        loadSprite()
    }

    fun loadCoins() {
        viewModelScope.launch {
            try {
                val response = decorationRepository.getCoins()
                _uiState.update { it.copy(coins = response.coins) }
            } catch (e: Exception) {
                // Fallback/ignore if API is not running/mocked
            }
        }
    }

    fun loadSprite() {
        viewModelScope.launch {
            try {
                val response = decorationRepository.getSprite()
                _uiState.update { it.copy(spriteImageUrl = response.imageUrl) }
            } catch (e: Exception) {
                // Fallback/ignore if API is not running/mocked
            }
        }
    }

    fun startOrResume() {
        if (_uiState.value.isRunning) return

        wasRunningBeforeBackground = false
        _uiState.update { it.copy(isRunning = true) }

        timerJob = viewModelScope.launch {
            while (_uiState.value.isRunning) {
                delay(1000L)
                _uiState.update { current ->
                    if (current.isRunning) {
                        current.copy(elapsedSeconds = current.elapsedSeconds + 1)
                    } else {
                        current
                    }
                }
            }
        }
    }

    fun endSession() {
        val seconds = _uiState.value.elapsedSeconds
        stopTicker()

        viewModelScope.launch {
            if (seconds > 0) {
                try {
                    val response = decorationRepository.submitStudyTime(seconds)
                    if (response.success) {
                        _uiState.update {
                            it.copy(
                                coins = response.totalCoins,
                                elapsedSeconds = 0,
                                isRunning = false
                            )
                        }
                    } else {
                        _uiState.update { it.copy(elapsedSeconds = 0, isRunning = false) }
                    }
                } catch (e: Exception) {
                    _uiState.update { it.copy(elapsedSeconds = 0, isRunning = false) }
                }
            } else {
                _uiState.update { it.copy(elapsedSeconds = 0, isRunning = false) }
            }

            // TODO: Navigation event to the reward/reveal screen implemented later
        }
    }

    fun onAppBackgrounded() {
        if (_uiState.value.isRunning) {
            wasRunningBeforeBackground = true
            stopTicker()
        }
    }

    fun onAppForegrounded() {
        if (wasRunningBeforeBackground) {
            startOrResume()
        }
    }

    private fun stopTicker() {
        timerJob?.cancel()
        timerJob = null
        _uiState.update { it.copy(isRunning = false) }
    }
}
