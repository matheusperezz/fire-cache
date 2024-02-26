package br.com.firecache.data.datasource

import br.com.firecache.data.dao.GenreDao
import br.com.firecache.domain.entities.Genre
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalGenreDataSourceImpl @Inject constructor(
    private val genreDao: GenreDao
): LocalGenreDataSource {
    override fun fetchAll(): Flow<List<Genre>> {
        return genreDao.fetchAllGenres()
    }

    override fun fetchById(genreId: String): Flow<Genre?> {
        return genreDao.findGenreById(genreId)
    }

    override suspend fun insert(genre: Genre) {
        genreDao.insert(genre)
    }

    override suspend fun delete(genre: Genre) {
        genreDao.delete(genre)
    }

    override suspend fun update(genre: Genre) {
        genreDao.update(genre)
    }

}