package bep.lingogame.domain

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@DisplayName("Turn Domain")
class TurnTest {

    @Test
    @DisplayName("getTurn")
    fun testStartTurn() {
        val turn = Turn(null, 0, "start", "Testing", "", 7, Game(null, true, Player(null, "Test Player", 0, null), null), null )
        assertEquals("Testing", turn.word)
        assertEquals(7, turn.wordLength)
    }
}