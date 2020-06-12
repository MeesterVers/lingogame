package bep.lingogame.domain

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Word(
        @Id
        @GeneratedValue
        val id: Int? = null,
        val word: String,
        val amountOfLetters: Int
)