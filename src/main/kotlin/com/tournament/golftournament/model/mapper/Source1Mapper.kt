package com.tournament.golftournament.model.mapper

import com.tournament.golftournament.model.dto.TournamentDto
import com.tournament.golftournament.model.validator.SourceValidator.validateInput
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Component
class Source1Mapper() : SourceMapper {
    override val source: String = "source_1"
    override val baseKeys = setOf("tournamentId", "courseName", "countryCode", "startDate", "endDate", "roundCount")
    override fun map(input: Map<String, String>): TournamentDto {
        validateInput(baseKeys,input)
        return TournamentDto(
            externalId = input["tournamentId"] as String,
            startDate = mapDate(input["startDate"] as String),
            endDate = mapDate(input["endDate"] as String),
            courseName = input["courseName"] as String,
            country = input["countryCode"] as String,
            roundCount = (input["roundCount"] as String).toInt(),
            source = source,
            additionalData = input.filterKeys { it !in baseKeys }.mapValues { it.value })
    }

    override fun mapDate(date: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yy")
        return LocalDate.parse(date, formatter)
            .atStartOfDay().atZone(ZoneOffset.UTC).toLocalDateTime()
    }
}