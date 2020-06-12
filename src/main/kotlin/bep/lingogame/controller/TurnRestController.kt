package bep.lingogame.controller

import org.springframework.http.MediaType
import bep.lingogame.domain.Turn
import org.springframework.web.bind.annotation.*
import bep.lingogame.service.TurnService

@RestController
@RequestMapping("/api/turn")
class TurnRestController(private val turnService: TurnService) {


    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createNew(@RequestBody requestBody: SaveTurnRequest): Turn? {

        val createdTurn =  turnService.createNew(requestBody);
        if (createdTurn != null) {

            val turnInfo = Turn(
                    createdTurn.id,
                    createdTurn.chances,
                    createdTurn.status,
                    "",
                    createdTurn.wordLength,
                    createdTurn.game,
                    createdTurn.createdAt
            )

            return turnInfo
        }
        return null
    }

    data class SaveTurnRequest(
            var game: Int
    )
}