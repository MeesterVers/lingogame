package bep.lingogame.repositories

import org.springframework.data.repository.CrudRepository
import bep.lingogame.domain.Word

interface WordRepository: CrudRepository<Word, Long>