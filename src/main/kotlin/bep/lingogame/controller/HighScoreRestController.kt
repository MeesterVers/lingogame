package bep.lingogame.controller

import bep.lingogame.domain.Player
import bep.lingogame.service.PlayerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/highscore")
class HighScoreRestController( private val playerService: PlayerService) {

    @GetMapping("/")
   fun getScoreList(): List<Player>{
       return playerService.findAllByOrderByScore()
   }
}