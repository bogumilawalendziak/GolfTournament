package com.tournament.golftournament.model.validator

import com.tournament.golftournament.exception.InvalidTournamentDataException

object SourceValidator {

    fun validateInput(baseKeys: Set<String>, input: Map<String, String>) {
        val missingKeys = baseKeys.filter { input[it].isNullOrBlank() }
        if (missingKeys.isNotEmpty()) {
            throw InvalidTournamentDataException("Missing or blank required fields: ${missingKeys.joinToString()}")
        }
    }
}