package bep.lingogame.domain

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Turn(
        @Id
        @GeneratedValue
        val id: Int? = null,
        val chances: Int,
        val status: String,
//        @OneToOne val word: Word,
        val word: String,
        val wordLength: Int,
        @ManyToOne val game: Game,
        @CreationTimestamp
        val createdAt: LocalDateTime?
)