package bep.lingogame.service

import bep.lingogame.controller.GameRestController
import bep.lingogame.domain.Game
import bep.lingogame.repositories.GameRepository
import org.springframework.stereotype.Service
import bep.lingogame.repositories.PlayerRepository
import java.time.LocalDateTime

@Service
class GameService(
        private val gameRepository: GameRepository,
        private val playerRepository: PlayerRepository) {

    fun createNew(requestBody: GameRestController.SaveGameRequest): Game {

        val game = Game(
                null,
                true,
                playerRepository.findById(requestBody.player),
                LocalDateTime.now()
        )
        gameRepository.save(game);
        return game
    }
}