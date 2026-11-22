package com.ts.touchdownstudy.data.repository

import com.ts.touchdownstudy.data.remote.TouchdownApiService
import com.ts.touchdownstudy.model.CoinResponse
import com.ts.touchdownstudy.model.Decoration
import com.ts.touchdownstudy.model.LeaderboardEntry
import com.ts.touchdownstudy.model.StudyTimeRequest
import com.ts.touchdownstudy.model.StudyTimeResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DecorationRepository @Inject constructor(
    private val apiService: TouchdownApiService
) {

    suspend fun getCoins(): CoinResponse {
        return apiService.getCoins()
    }

    suspend fun getLeaderboard(): List<LeaderboardEntry> {
        return apiService.getLeaderboard()
    }

    suspend fun submitStudyTime(seconds: Int): StudyTimeResponse {
        return apiService.submitStudyTime(StudyTimeRequest(studyTimeSeconds = seconds))
    }

    suspend fun getDecorations(): List<Decoration> {
        return apiService.getDecorations()
    }

    suspend fun buyDecoration(decorationId: String): Decoration {
        return apiService.buyDecoration(decorationId)
    }
}
