package bep.lingogame.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import bep.lingogame.service.TurnService

@RestController
@RequestMapping("/api/turn/guess")
class GuessRestController(private val turnService: TurnService) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun guessedWord(@RequestBody requestBody: GuessedInfo): Any? {

        val turnResponse =  turnService.checkGuessedWord(requestBody);
        return turnResponse
    }

    data class GuessedInfo(
            var turn: Int,
            var guessedWord: String
    )
}