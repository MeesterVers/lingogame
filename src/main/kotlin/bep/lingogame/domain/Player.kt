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
    val id: Long? = null,
    val name: String,
    val score: Int,
    @CreationTimestamp
    val createdAt: LocalDateTime?
)