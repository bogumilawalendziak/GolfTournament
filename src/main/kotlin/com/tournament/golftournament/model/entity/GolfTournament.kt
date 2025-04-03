package com.tournament.golftournament.model.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "golf_tournaments")
data class GolfTournament(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val externalId: String,
    @Column(nullable = false)
    val startDate: LocalDate,
    @Column(nullable = false)
    val endDate: LocalDate,
    @Column(nullable = false)
    val courseName: String,
    @Column(nullable = false)
    val country: String,
    @Column(nullable = false)
    val source: String,
    @Column(nullable = false)
    val roundCount: Int,
    @Column(columnDefinition = "TEXT")
    val additionalData: String? = null,
)
