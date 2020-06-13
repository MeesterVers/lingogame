package bep.lingogame.domain

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Player (
        @Id
    @GeneratedValue
    val id: Int? = null,
        val name: String,
        var score: Int,
        @CreationTimestamp
    val createdAt: LocalDateTime?
)