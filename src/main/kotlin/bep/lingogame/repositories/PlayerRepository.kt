package bep.lingogame.repositories

import org.springframework.data.repository.CrudRepository
import bep.lingogame.domain.Player

interface PlayerRepository: CrudRepository<Player, Long>{
    fun findById(id: Int) : Player?
}