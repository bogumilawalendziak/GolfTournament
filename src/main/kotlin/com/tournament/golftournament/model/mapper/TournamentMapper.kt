package com.tournament.golftournament.model.mapper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tournament.golftournament.model.dto.TournamentDto
import com.tournament.golftournament.model.entity.GolfTournament

fun TournamentDto.toEntity(objectMapper: ObjectMapper): GolfTournament = GolfTournament(
    externalId = this.externalId,
    startDate = this.startDate.toLocalDate(),
    endDate = this.endDate.toLocalDate(),
    courseName = this.courseName,
    country = this.country,
    source = this.source,
    roundCount = this.roundCount,
    additionalData = this.additionalData?.let { objectMapper.writeValueAsString(it) }
)

fun GolfTournament.toDto(objectMapper: ObjectMapper): TournamentDto = TournamentDto(
    externalId = this.externalId,
    startDate = this.startDate.atStartOfDay(),
    endDate = this.endDate.atStartOfDay(),
    courseName = this.courseName,
    country = this.country,
    source = this.source,
    roundCount = this.roundCount,
    additionalData = this.additionalData?.let { objectMapper.readValue<Map<String, String>>(it) })