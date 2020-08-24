package bep.lingogame.domain

import org.hibernate.annotations.CreationTimestamp
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import java.time.LocalDateTime
import javax.persistence.OneToOne

@Entity
data class Game(
        @Id
        @GeneratedValue
        val id: Int? = null,
        val status: Boolean,
        @OneToOne val player: Player?,
        @CreationTimestamp
        val createdAt: LocalDateTime?
)