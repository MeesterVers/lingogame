package bep.lingogame.domain

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@DisplayName("Game Domain")
class GameTest {

    @Test
    @DisplayName("GetGame")
    fun testStartTurn() {
        val game = Game(null, true, Player(null, "Test Player", 0, null), null)
        assertEquals(true, game.status)
    }
}