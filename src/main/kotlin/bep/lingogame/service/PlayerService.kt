package bep.lingogame.service

import bep.lingogame.domain.Player
import org.springframework.stereotype.Service
import bep.lingogame.controller.PlayerRestController
import bep.lingogame.repositories.PlayerRepository
import java.time.LocalDateTime

@Service
class PlayerService(private val playerRepository: PlayerRepository) {
    fun findAll(): MutableIterable<Player> {
        return playerRepository.findAll()
    }

    fun createNew(requestBody: PlayerRestController.SavePlayerRequest): Player {
        val player = Player(
                null,
                requestBody.name,
                requestBody.score,
                LocalDateTime.now()
        )
        return playerRepository.save(player);
    }
}