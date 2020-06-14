package bep.lingogame.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class WordService() {
    fun getWord(): String? {
        val uri = "https://lingo-words-api.herokuapp.com/api/words/"

        val restTemplate = RestTemplate()
        val wordList = restTemplate.getForObject(uri, List::class.java)

        if (wordList != null) {
            val wordListSize = wordList.size
            val randomNumber = (0..wordListSize).random()

            return wordList[randomNumber].toString()
        }else{
            return null
        }
    }
}