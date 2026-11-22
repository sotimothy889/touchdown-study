package com.ts.touchdownstudy.data.remote

import com.ts.touchdownstudy.model.CoinResponse
import com.ts.touchdownstudy.model.Decoration
import com.ts.touchdownstudy.model.LeaderboardEntry
import com.ts.touchdownstudy.model.StudyTimeRequest
import com.ts.touchdownstudy.model.StudyTimeResponse
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

    @GET("api/decorations")
    suspend fun getDecorations(): List<Decoration>

    @POST("api/decorations/{id}/buy")
    suspend fun buyDecoration(@Path("id") decorationId: String): Decoration
}
