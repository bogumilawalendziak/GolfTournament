package com.tournament.golftournament.controller

import com.tournament.golftournament.model.dto.TournamentDto
import com.tournament.golftournament.service.TournamentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tournaments")
class TournamentController(private val service: TournamentService) {
    @PostMapping
    fun saveTournament(@RequestHeader("xSource") source: String, @RequestBody json: String): ResponseEntity<String> {
        val externalId = service.processAndSave(source, json)
        return ResponseEntity.status(HttpStatus.CREATED).body(externalId)
    }

    @GetMapping("/by-externalId")
    fun getByExternalId(@RequestHeader("xSource") source: String, @RequestParam externalId: String,): TournamentDto = service.findByExternalId(externalId)

    @GetMapping("/by-source")
    fun getAllBySource(@RequestParam source: String,): List<TournamentDto> = service.findAllBySource(source)

    @GetMapping("/by-course")
    fun getAllByCourse(@RequestParam course: String,): List<TournamentDto> = service.findAllByCourseName(course)

    @GetMapping("/by-country")
    fun getAllByCountryCode(@RequestParam countryCode: String,): List<TournamentDto> = service.getAllTournamentsByCountryCode(countryCode)
}