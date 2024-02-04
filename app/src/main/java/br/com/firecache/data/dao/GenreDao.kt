package br.com.firecache.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.firecache.data.models.BookCardModel
import br.com.firecache.data.models.Genre
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query("SELECT * FROM Genre")
    fun fetchAllGenres(): Flow<List<Genre>>

    @Query("SELECT * FROM BookCardModel WHERE genreId = :genreId")
    fun fetchBooksFromGenre(genreId: String): Flow<List<BookCardModel>>

    @Query("SELECT * FROM Genre WHERE id = :genreId")
    fun findGenreById(genreId: String): Flow<Genre>

    @Insert
    suspend fun insert(genre: Genre)
}