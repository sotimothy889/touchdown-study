package com.ts.touchdownstudy.data.repository

import com.ts.touchdownstudy.data.remote.TouchdownApiService
import com.ts.touchdownstudy.model.CoinResponse
import com.ts.touchdownstudy.model.Decoration
import com.ts.touchdownstudy.model.DecorationImageResponse
import com.ts.touchdownstudy.model.LeaderboardEntry
import com.ts.touchdownstudy.model.SpriteResponse
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

    suspend fun getSprite(): SpriteResponse {
        return apiService.getSprite()
    }

    suspend fun getDecorations(): List<Decoration> {
        return apiService.getDecorations()
    }

    suspend fun getDecorationImages(): List<DecorationImageResponse> {
        return apiService.getDecorationImages()
    }

    suspend fun getDecorationImage(id: String): DecorationImageResponse {
        return apiService.getDecorationImage(id)
    }

    suspend fun buyDecoration(): Decoration {
        return apiService.buyDecoration()
    }
}
