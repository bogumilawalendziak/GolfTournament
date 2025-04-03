package com.tournament.golftournament

import com.tournament.golftournament.service.TournamentService
import org.hibernate.validator.internal.util.Contracts.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GolfTournamentApplicationTests {

    @Autowired
    lateinit var service: TournamentService

    @Test
    fun `service should not be null`() {
        assertNotNull(service)
    }

}
