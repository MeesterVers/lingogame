package bep.lingogame.repositories

import org.springframework.data.repository.CrudRepository
import bep.lingogame.domain.Game

interface GameRepository: CrudRepository<Game, Long>