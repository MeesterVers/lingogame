package bep.lingogame.controller

import org.springframework.http.MediaType
import bep.lingogame.domain.Player
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import bep.lingogame.service.PlayerService

@RestController
@RequestMapping("/api/player")
class PlayerRestController(private val playerService: PlayerService) {

    @GetMapping
    fun findAll(): MutableIterable<Player> {
        return playerService.findAll()
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createNew(@RequestBody requestBody: SavePlayerRequest): Player? {

            val createdPlayer =  playerService.createNew(requestBody);
            if (!createdPlayer.equals(null)){
                return createdPlayer
            }else{
                return null
            }
    }

    data class SavePlayerRequest(
            val name: String
    )
}