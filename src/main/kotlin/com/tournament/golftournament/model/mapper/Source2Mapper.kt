package com.tournament.golftournament.model.mapper

import com.tournament.golftournament.model.dto.TournamentDto
import com.tournament.golftournament.model.validator.SourceValidator.validateInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Component
class Source2Mapper() : SourceMapper {

    @Autowired
    lateinit var countryMapper: CountryMapper

    override val source: String = "source_2"
    override val baseKeys = setOf("tournamentUUID", "golfCourse", "hostCountry", "epochStart", "epochFinish", "rounds")
    override fun map(input: Map<String, String>): TournamentDto {
        validateInput(baseKeys, input)
        return TournamentDto(
            externalId = input["tournamentUUID"] as String,
            startDate = mapDate(input["epochStart"] as String),
            endDate = mapDate(input["epochFinish"] as String),
            courseName = input["golfCourse"] as String,
            country = getCountryCode(input["hostCountry"]),
            roundCount = (input["rounds"] as String).toInt(),
            source = source,
            additionalData = input.filterKeys { it !in baseKeys }.mapValues { it.value })
    }

    override fun mapDate(date: String): LocalDateTime {
        return Instant.ofEpochSecond(date.toLong()).atZone(ZoneOffset.UTC).toLocalDateTime();
    }

    fun getCountryCode(countryName: String?): String {
        return countryMapper.getCountryCode(countryName!!)
    }
}