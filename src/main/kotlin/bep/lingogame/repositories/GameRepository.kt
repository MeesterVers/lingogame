package bep.lingogame.repositories

import org.springframework.data.repository.CrudRepository
import bep.lingogame.domain.Game
import java.util.*

interface GameRepository: CrudRepository<Game, Long>{
    fun findById(id: Int): Game
}