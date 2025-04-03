package com.tournament.golftournament.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tournament.golftournament.controller.TournamentController
import com.tournament.golftournament.model.dto.TournamentDto
import com.tournament.golftournament.model.mapper.SourceMapperFactory
import com.tournament.golftournament.model.mapper.toDto
import com.tournament.golftournament.model.mapper.toEntity
import com.tournament.golftournament.repository.TournamentRepository
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.stereotype.Service

@Service
@Slf4j
class TournamentService(
    private val repository: TournamentRepository,
    private val objectMapper: ObjectMapper,
    private val tournamentMapper: SourceMapperFactory
) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(TournamentController::class.java)
    }

    fun findByExternalId(externalId: String): TournamentDto {
        println(repository.findByExternalId(externalId))
        return repository.findByExternalId(externalId)?.toDto(objectMapper).also { log.info("Found '$externalId' tournament") }
            ?: throw NoSuchElementException("Tournament with externalId=$externalId not found")
    }

    fun findAllBySource(source: String): List<TournamentDto> {
        return repository.findAllBySource(source).map { it.toDto(objectMapper) }
            .ifEmpty { throw NoSuchElementException("Tournaments with source=$source not found") }

    }

    fun findAllByCourseName(courseName: String): List<TournamentDto> {
        return repository.findAllBySource(courseName).map { it.toDto(objectMapper) }
            .ifEmpty { throw NoSuchElementException("Tournaments for courseName=$courseName not found") }
    }

    fun getAllTournamentsByCountryCode(country: String): List<TournamentDto> {
        return repository.findAllByCountry(country).map { it.toDto(objectMapper) }
    }

    fun processAndSave(source: String, json: String): String {
        return tournamentMapper.getMapper(source).map(objectMapper.readValue<Map<String, String>>(json))
            .let { save(it) }
    }

    private fun save(dto: TournamentDto): String {
        return repository.findByExternalId(dto.externalId)?.externalId ?: repository.save(dto.toEntity(objectMapper)).externalId
    }
}