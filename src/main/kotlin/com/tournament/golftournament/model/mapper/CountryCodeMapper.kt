package com.tournament.golftournament.model.mapper

import com.neovisionaries.i18n.CountryCode
import com.tournament.golftournament.config.CountryOverrideProperties
import com.tournament.golftournament.exception.InvalidTournamentDataException
import org.springframework.stereotype.Component

@Component
class CountryMapper(private val countries: CountryOverrideProperties) {

        fun getCountryCode(countryName: String): String {
            println(countries.countries.toString())
            countries.countries.entries.find { it.value.equals(countryName, ignoreCase = true) }?.key?.let { return it }
            val match = CountryCode.findByName("(?i)^$countryName\$").firstOrNull()
            return match?.alpha2 ?: throw InvalidTournamentDataException("Could not map country to code: $countryName")
        }
    }

