package bep.lingogame

import bep.lingogame.service.TurnService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.Banner
import bep.lingogame.service.WordService

@SpringBootApplication
class LingogameApplication

//fun checkWordsLetterForLetter(word: String, guessedWord: String): Any?{
//	var wordAfterGuess = ""
//
//	for (index in word.indices) {
////		println(word[index])
////		println(index)
//
//		val compareLetters = word[index].compareTo(guessedWord[index])
//
//		if (compareLetters == 0){
////			println("letter goed")
////			println(guessedWord[index])
//			wordAfterGuess += guessedWord[index]
//		}else{
//			wordAfterGuess += 0
//		}
//	}
////	println(wordAfterGuess)
//	return word
//}

fun main(args: Array<String>) {
	runApplication<LingogameApplication>(*args){
		setBannerMode(Banner.Mode.OFF)
	}
//	checkWordsLetterForLetter("Waterman", "Wagenman")
}
