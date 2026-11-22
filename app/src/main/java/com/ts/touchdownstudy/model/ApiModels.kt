package com.ts.touchdownstudy.model

import kotlinx.serialization.Serializable

@Serializable
data class CoinResponse(
    val coins: Int = 0
)

@Serializable
data class LeaderboardEntry(
    val userId: String,
    val username: String,
    val studyTimeSeconds: Int,
    val rank: Int
)

@Serializable
data class StudyTimeRequest(
    val studyTimeSeconds: Int
)

@Serializable
data class StudyTimeResponse(
    val success: Boolean,
    val earnedCoins: Int,
    val totalCoins: Int
)

@Serializable
data class Decoration(
    val id: String,
    val name: String,
    val cost: Int,
    val isUnlocked: Boolean,
    val imageUrl: String = ""
)
