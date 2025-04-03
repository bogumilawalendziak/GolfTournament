package com.tournament.golftournament.service

import com.tournament.golftournament.TournamentFactory
import com.tournament.golftournament.exception.InvalidTournamentDataException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest

@EnableConfigurationProperties
@SpringBootTest
class TournamentServiceTest {

    @Autowired
    lateinit var service: TournamentService

    @Test
    fun `should save valid tournament to DB and return externalId for source_1`() {
        //given:
        val input = TournamentFactory.getValidJsonTournamentSource_1()
        val source = "source_1"

        //when:
        val output = service.processAndSave(source, input)

        //then:
        println(output)
        assert(output.isNotBlank())
    }

    @Test
    fun `should save valid tournament to DB and return externalId for source_2`() {
        //given:
        val input = TournamentFactory.getValidJsonTournamentSource_2()
        val source = "source_2"

        //when:
        val output = service.processAndSave(source, input)

        //then:
        println(output)
        assert(output.isNotBlank())
    }

    @Test
    fun `should throw error for invalid json`() {
        //given:
        val input = TournamentFactory.getInvalidJsonTournamentSource_1()
        val source = "source_1"

        //then:
        assertThrows<InvalidTournamentDataException> { service.processAndSave(source, input) }
    }

    @Test
    fun `should throw error for invalid mapper`() {
        //given:
        val input = TournamentFactory.getValidJsonTournamentSource_1()
        val source = "source_2"

        //then:
        assertThrows<InvalidTournamentDataException> { service.processAndSave(source, input) }
    }
}
