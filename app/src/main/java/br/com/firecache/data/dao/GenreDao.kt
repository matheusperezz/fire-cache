package br.com.firecache.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.firecache.domain.entities.Book
import br.com.firecache.domain.entities.Genre
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query("SELECT * FROM Genre")
    fun fetchAllGenres(): Flow<List<Genre>>

    @Query("SELECT * FROM Book WHERE genreId = :genreId")
    fun fetchBooksFromGenre(genreId: String): Flow<List<Book>>

    @Query("SELECT * FROM Genre WHERE id = :genreId")
    fun findGenreById(genreId: String): Flow<Genre>

    @Insert
    suspend fun insert(genre: Genre)

    @Delete
    suspend fun delete(genre: Genre)

    @Update
    suspend fun update(genre: Genre)
}