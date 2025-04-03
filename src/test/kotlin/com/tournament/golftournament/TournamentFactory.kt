package com.tournament.golftournament

import com.tournament.golftournament.model.entity.GolfTournament
import java.time.LocalDate

class TournamentFactory {
    companion object {
        @JvmStatic
        fun getValidTournament(): GolfTournament = GolfTournament(
            externalId = "123456",
            startDate = LocalDate.now(),
            endDate = LocalDate.now().plusDays(1),
            courseName = "course_1",
            country = "Poland",
            roundCount = 1,
            source = "source_1",
            additionalData = """{"key":"value"}"""
        )

        @JvmStatic
        fun getValidJsonTournamentSource_1(): String = """
            {
            	"tournamentId": "174638",
            	"tournamentName": "Women's Open Championship",
            	"forecast": "fair",
            	"courseName": "Sunnydale Golf Course",
            	"countryCode": "GB",
            	"startDate": "09/07/21",
            	"endDate": "13/07/21",
            	"roundCount": "4"
            }
        """.trimIndent()


        @JvmStatic
        fun getValidJsonTournamentSource_2(): String = """
            {
    "tournamentUUID":"87fc6650-e114-4179-9aef-6a9a13030f80",
    "golfCourse":"Happy Days Golf Club",
    "competitionName":"South West Invitational",
    "hostCountry":"United States Of America",
    "epochStart":"1638349200",
    "epochFinish":"1638468000",
    "rounds":"2",
    "playerCount":"35"
        }
        """.trimIndent()


        @JvmStatic
        fun getInvalidJsonTournamentSource_1(): String = """
            {
                 "tournamentUUID":"87fc6650-e114-4179-9aef-6a9a13030f80",
    "golfCourse":"Happy Days Golf Club",
    "competitionName":"South West Invitational",
    "hostCountry":"invalid country",
    "epochStart":"1638349200",
    "epochFinish":"1638468000",
    "rounds":"2",
    "playerCount":"35"
        }
        """.trimIndent()

    }
}