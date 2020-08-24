package bep.lingogame.domain

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@DisplayName("Player Domain")
class PlayerTest {

    @Test
    @DisplayName("getPlayer")
    fun testGetPLayer() {
        val player = Player(null, "Test Player", 0, null)
        assertEquals("Test Player", player.name)
        assertEquals(0, player.score)
    }

}