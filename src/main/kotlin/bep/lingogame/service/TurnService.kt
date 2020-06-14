package bep.lingogame.service

import bep.lingogame.controller.GuessRestController
import bep.lingogame.controller.TurnRestController
import bep.lingogame.domain.Player
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
                    "",
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
                    foundTurn.wordGuessedSoFar,
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
        var score = 60
        var guessedWord = (requestBody.guessedWord).toLowerCase()
        var word = (turn.word).toLowerCase()

        //set chance
        val chancesResponse = setTurnChances(turn, 1)

        if (turn.status.equals("start", true)) {
            if (chancesResponse) {

                if (checkWordLength(word, guessedWord)) {
                    if (checkWordsAreTheSame(word, guessedWord)) {

                        if (turn.game.player != null) {
                            score -= (turn.chances * 10)

                            playerService.addToScore(turn.game.player, score)
                            endTurn(turn)
                            response = arrayOf("Woord goed geraden", "Naam: " + turn.game.player.name, "Score: " + turn.game.player.score, "Start nieuwe beurt")

                        }
                    } else {
                        response = checkWordsLetterForLetter(turn, guessedWord, turn.chances)
                    }
                } else {
                    response = arrayOf("Woord lengte ongelijk")
                }


            } else {
                response = arrayOf("Geen kansen meer.. Start nieuwe beurt")
            }
        }else{
            response = arrayOf("Woord al goed geraden.. Start nieuwe beurt")
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

    fun checkWordsLetterForLetter(turn: Turn, guessedWord: String, chances: Int): Array<Any>{
        var wordAfterGuess = ""
        var response: Array<Any>
        var word = (turn.word).toLowerCase()

        for (index in word.indices) {
            val compareLetters = word[index].compareTo(guessedWord[index])

            if (compareLetters == 0){
                wordAfterGuess += guessedWord[index]
            }else{
                wordAfterGuess += 0
            }
        }
        setGuessedSoFar(turn, wordAfterGuess)
        response = arrayOf("Uw heeft deze letters goed: " + wordAfterGuess, "Kansen: " + chances)
        return response
    }

    fun setTurnChances(turn: Turn, chances: Int): Boolean{

        if (turn.chances < 5) {
            var newChances = turn.chances + chances

            val updatedTurn = Turn(
                    turn.id,
                    newChances,
                    turn.status,
                    turn.word,
                    turn.wordGuessedSoFar,
                    turn.wordLength,
                    turn.game,
                    turn.createdAt
            )
            turnRepository.save(updatedTurn)
            return true
        }else{
            return false
        }
    }

    fun setGuessedSoFar(turn: Turn, wordAfterGuess: String){
        val updatedTurn = Turn(
                turn.id,
                turn.chances,
                turn.status,
                turn.word,
                wordAfterGuess,
                turn.wordLength,
                turn.game,
                turn.createdAt
        )
        turnRepository.save(updatedTurn)
    }

    fun endTurn(turn: Turn){
        val updatedTurn = Turn(
                turn.id,
                turn.chances,
                "done",
                turn.word,
                turn.wordGuessedSoFar,
                turn.wordLength,
                turn.game,
                turn.createdAt
        )
        turnRepository.save(updatedTurn)
    }
}