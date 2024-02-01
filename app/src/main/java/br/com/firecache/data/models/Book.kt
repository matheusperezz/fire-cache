package br.com.firecache.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Genre::class,
            parentColumns = ["id"],
            childColumns = ["genreId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BookCardModel(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val imageUrl: String,
    val topic: String,
    val genreId: String
)
