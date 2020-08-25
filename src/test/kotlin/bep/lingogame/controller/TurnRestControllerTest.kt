package bep.lingogame.controller

import bep.lingogame.repositories.GameRepository
import bep.lingogame.repositories.PlayerRepository
import bep.lingogame.repositories.TurnRepository
import bep.lingogame.service.PlayerService
import bep.lingogame.service.TurnService
import bep.lingogame.service.WordService
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@DisplayName("Player Domain")
class TurnRestControllerTest(
        @InjectMocks
        private val turnRestController: TurnRestController
) {

    @Mock
    private lateinit var turnService: TurnService
    private lateinit var gameRepository: GameRepository
    private lateinit var playerRepository: PlayerRepository
    private lateinit var playerService: PlayerService
    private lateinit var turnRepository: TurnRepository
    private lateinit var wordService: WordService

    @BeforeEach
    fun configureSystemTest(){
    }
}