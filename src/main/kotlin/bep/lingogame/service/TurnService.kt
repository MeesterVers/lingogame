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
        private val playerService: PlayerService,
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

    fun findById(id: Int): Any? {
        val foundTurn = turnRepository.getById(id)

        if (foundTurn != null) {

            val wordFirstLetter = (foundTurn.word.toString())[0]

            val turn = Turn(
                    foundTurn.id,
                    foundTurn.chances,
                    foundTurn.status,
                    wordFirstLetter.toString(),
                    foundTurn.wordLength,
                    foundTurn.game,
                    foundTurn.createdAt
            )
            return turn
        }
        return "Bestaat niet"
    }

    //game logic..............................
    fun checkGuessedWord(requestBody: GuessRestController.GuessedInfo): Any? {
        val turn = turnRepository.getById(requestBody.turn)
        var response: Array<Any> = arrayOf()

        if (checkWordLength(turn.word, requestBody.guessedWord)){
            if (checkWordsAreTheSame(turn.word, requestBody.guessedWord)){

                if (turn.game.player != null) {

                    playerService.addToScore(turn.game.player, 50)
                    response = arrayOf("Woord goed geraden", "Naam:", turn.game.player.name, "Score:", turn.game.player.score)
                    // add to score
                    // new turn
                }
            }else{
//                response = arrayOf("Incorrect woord")
               response = checkWordsLetterForLetter(turn.word, requestBody.guessedWord)
            }
        }else{
            response = arrayOf("Woord lengte ongelijk")
        }


        return response
    }

    fun checkWordLength(word: String, guessedWord: String): Boolean{
        if(word.length == guessedWord.length){
            return true
        }
        return false
    }

    fun checkWordsAreTheSame(word: String, guessedWord: String): Boolean{
        val compareWords = guessedWord.compareTo(word, true)

        if(compareWords == 0){
            return true
        }
        return false
    }

    fun checkWordsLetterForLetter(word: String, guessedWord: String): Array<Any>{
        var wordAfterGuess = ""
        var response: Array<Any> = arrayOf()

        for (index in word.indices) {
            val compareLetters = word[index].compareTo(guessedWord[index])

            if (compareLetters == 0){
                wordAfterGuess += guessedWord[index]
            }else{
                wordAfterGuess += 0
            }
        }
        response = arrayOf("Uw heeft deze letters goed", wordAfterGuess)
        return response
    }

}