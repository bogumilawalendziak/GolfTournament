package com.tournament.golftournament.repository

import com.tournament.golftournament.model.entity.GolfTournament
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TournamentRepository : JpaRepository<GolfTournament, Long> {
    fun findByExternalId(externalId: String): GolfTournament?
    fun findAllByCountry(country: String): List<GolfTournament>
    fun findAllBySource(source: String): List<GolfTournament>
    fun findAllByCourseName(courseName: String): List<GolfTournament>

}