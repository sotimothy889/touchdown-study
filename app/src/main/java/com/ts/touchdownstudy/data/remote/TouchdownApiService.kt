package com.ts.touchdownstudy.data.remote

import com.ts.touchdownstudy.model.CoinResponse
import com.ts.touchdownstudy.model.Decoration
import com.ts.touchdownstudy.model.DecorationImageResponse
import com.ts.touchdownstudy.model.LeaderboardEntry
import com.ts.touchdownstudy.model.SpriteResponse
import com.ts.touchdownstudy.model.StudyTimeRequest
import com.ts.touchdownstudy.model.StudyTimeResponse
import com.ts.touchdownstudy.model.UpdateUsernameRequest
import com.ts.touchdownstudy.model.UserStats
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TouchdownApiService {

    @GET("api/coins")
    suspend fun getCoins(): CoinResponse

    @GET("api/leaderboard")
    suspend fun getLeaderboard(): List<LeaderboardEntry>

    @POST("api/study/submit")
    suspend fun submitStudyTime(@Body request: StudyTimeRequest): StudyTimeResponse

    @GET("api/sprite")
    suspend fun getSprite(): SpriteResponse

    @GET("api/decorations")
    suspend fun getDecorations(): List<Decoration>

    @GET("api/decorations/images")
    suspend fun getDecorationImages(): List<DecorationImageResponse>

    @GET("api/decorations/{id}/image")
    suspend fun getDecorationImage(@Path("id") id: String): DecorationImageResponse

    @POST("api/decorations/buy")
    suspend fun buyDecoration(): Decoration

    @GET("api/user/stats")
    suspend fun getUserStats(): UserStats

    @POST("api/user/username")
    suspend fun updateUsername(@Body request: UpdateUsernameRequest): UserStats
}
