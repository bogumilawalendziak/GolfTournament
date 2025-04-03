package com.tournament.golftournament.model.mapper

import com.tournament.golftournament.model.dto.TournamentDto
import java.time.LocalDateTime

interface SourceMapper {
    val source: String
    val baseKeys: Set<String>

    fun map(input: Map<String, String>): TournamentDto
    fun mapDate(date: String): LocalDateTime
}