package com.tournament.golftournament.model.dto

import java.time.LocalDateTime

data class TournamentDto(
    val externalId: String,
    val courseName: String,
    val country: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val roundCount: Int,
    val source: String,
    val additionalData: Map<String, String>? = null
)


