package bep.lingogame.controller

import org.springframework.http.MediaType
import bep.lingogame.domain.Game
import org.springframework.web.bind.annotation.*
import bep.lingogame.service.GameService

@RestController
@RequestMapping("/api/game")
class GameRestController(private val gameService: GameService) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createNew(@RequestBody requestBody: SaveGameRequest): Game? {

        val createdGame =  gameService.createNew(requestBody);
        if (!createdGame.equals(null)){
            return createdGame
        }else{
            return null
        }
    }

    data class SaveGameRequest(
            var player: Int
    )
}