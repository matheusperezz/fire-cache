package br.com.firecache.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookCardModel(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val imageUrl: String,
    val topic: String,
    val genre: String
)
