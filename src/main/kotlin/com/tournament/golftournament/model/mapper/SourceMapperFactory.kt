package com.tournament.golftournament.model.mapper

import com.tournament.golftournament.exception.InvalidTournamentDataException
import org.springframework.stereotype.Component

@Component
class SourceMapperFactory(mappers: List<SourceMapper>) {

    private val mapperMap: Map<String, SourceMapper> = mappers.associateBy { it.source }

    fun getMapper(source: String): SourceMapper =
        mapperMap[source] ?: throw InvalidTournamentDataException("No mapper found for source: $source")
}
