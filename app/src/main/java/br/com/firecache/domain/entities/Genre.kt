package br.com.firecache.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Genre(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String
)
