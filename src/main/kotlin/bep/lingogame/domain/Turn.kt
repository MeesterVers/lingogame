package bep.lingogame.domain

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Turn(
        @Id
        @GeneratedValue
        val id: Long? = null,
        val chances: Int,
        val status: Boolean,
        @OneToOne val word: Word,
        @ManyToOne val game: Game,
        @CreationTimestamp
        val createdAt: LocalDateTime?
)