package bep.lingogame.repositories

import org.springframework.data.repository.CrudRepository
import bep.lingogame.domain.Turn

interface TurnRepository: CrudRepository<Turn, Long>