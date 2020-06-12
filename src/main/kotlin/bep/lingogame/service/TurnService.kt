package bep.lingogame.service

import bep.lingogame.controller.GuessRestController
import bep.lingogame.controller.TurnRestController
import bep.lingogame.domain.Turn
import bep.lingogame.repositories.GameRepository
import org.springframework.stereotype.Service
import bep.lingogame.repositories.PlayerRepository
import bep.lingogame.repositories.TurnRepository

@Service
class TurnService (
        private val gameRepository: GameRepository,
        private val playerRepository: PlayerRepository,
        private val turnRepository: TurnRepository,
        private val wordService: WordService){

    fun createNew(requestBody: TurnRestController.SaveTurnRequest): Turn? {

        val word =  wordService.getWord()
        if (word != null){

            val turn = Turn(
                    null,
                    0,
                    "start",
                    word,
                    word.length,
                    gameRepository.findById(requestBody.game),
                    null
            )
            turnRepository.save(turn);
            return turn
        }
        return null

    }

    fun checkGuessLetter(requestBody: GuessRestController.GuessedInfo): Any? {
        val turn = turnRepository.getById(requestBody.turn)

        if (turn.word.contains(requestBody.guessedLetter)){
            var wordIndex = 0

            for (word in turn.word.indices) {
                wordIndex = turn.word[word].toInt()
            }

//            val firstChar: Char = turn.word[0]
            var response: Array<Any> = arrayOf("Letter komt wel voor", requestBody.guessedLetter, wordIndex)
            return response
        }
        return "Letter komt niet voor"
    }

}