package com.tournament.golftournament.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(InvalidTournamentDataException::class)
    fun handleInvalidData(e: InvalidTournamentDataException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleInvalidData(e: NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity.notFound().build()
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneric(e: Exception): ResponseEntity<String> {
        return ResponseEntity.status(500).body("Internal server error: ${e.message}")
    }
}