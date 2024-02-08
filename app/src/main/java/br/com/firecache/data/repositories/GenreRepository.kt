package br.com.firecache.data.repositories

import br.com.firecache.data.dao.GenreDao
import br.com.firecache.data.models.Book
import br.com.firecache.data.models.Genre
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GenreRepository {
    fun fetchAllGenres(): Flow<List<Genre>>
    fun fetchBooksFromGenre(genreId: String): Flow<List<Book>>
    fun findGenreById(genreId: String): Flow<Genre>
    suspend fun insert(genre: Genre)
}

class GenreRepositoryImpl @Inject constructor(
    private val genreDao: GenreDao
): GenreRepository {
    override fun fetchAllGenres(): Flow<List<Genre>> {
        return genreDao.fetchAllGenres()
    }

    override fun fetchBooksFromGenre(genreId: String): Flow<List<Book>> {
        return genreDao.fetchBooksFromGenre(genreId)
    }

    override fun findGenreById(genreId: String): Flow<Genre> {
        return genreDao.findGenreById(genreId)
    }

    override suspend fun insert(genre: Genre) {
        genreDao.insert(genre)
    }


}