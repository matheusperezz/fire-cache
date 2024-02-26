package br.com.firecache.data.repositories

import br.com.firecache.data.datasource.LocalGenreDataSource
import br.com.firecache.domain.entities.Genre
import br.com.firecache.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val localGenreDataSource: LocalGenreDataSource
): GenreRepository {
    override fun fetchAll(): Flow<List<Genre>> {
        return localGenreDataSource.fetchAll()
    }

    override fun fetchById(genreId: String): Flow<Genre?> {
        return localGenreDataSource.fetchById(genreId)
    }

    override suspend fun insert(genre: Genre) {
        localGenreDataSource.insert(genre)
    }

    override suspend fun delete(genre: Genre) {
        localGenreDataSource.delete(genre)
    }

    override suspend fun update(genre: Genre) {
        localGenreDataSource.update(genre)
    }

}