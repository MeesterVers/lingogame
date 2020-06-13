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
                0,
                null
        )
        playerRepository.save(player);
        return player
    }

    fun addToScore(player: Player, score: Int): Player {
        var newScore = player.score + score

        val updatedPlayer = Player (
                player.id,
                player.name,
                newScore,
                player.createdAt
        )
        return playerRepository.save(updatedPlayer)
    }

    fun removeFromScore(player: Player, score: Int): Player {
        var newScore = player.score - score

        val updatedPlayer = Player (
                player.id,
                player.name,
                newScore,
                player.createdAt
        )
        return playerRepository.save(updatedPlayer)
    }

}