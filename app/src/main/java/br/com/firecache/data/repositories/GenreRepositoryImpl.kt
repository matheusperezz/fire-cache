package br.com.firecache.data.repositories

import br.com.firecache.data.datasource.LocalGenreDataSource
import br.com.firecache.data.datasource.RemoteGenreDataSource
import br.com.firecache.domain.entities.CreateGenre
import br.com.firecache.domain.entities.Genre
import br.com.firecache.domain.repository.GenreRepository
import br.com.firecache.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val localGenreDataSource: LocalGenreDataSource,
    private val remoteGenreDataSource: RemoteGenreDataSource,
    private val networkUtils: NetworkUtils
) : GenreRepository {
    override fun fetchAll(): Flow<List<Genre>> {
        return localGenreDataSource.fetchAll()
    }

    override fun fetchById(genreId: String): Flow<Genre?> {
        return localGenreDataSource.fetchById(genreId)
    }

    override suspend fun insert(genre: Genre) {
        if (networkUtils.isInternetAvailable()) {
            remoteGenreDataSource.insert(CreateGenre(genre))
            localGenreDataSource.insert(genre)
            return
        }
        localGenreDataSource.insert(genre)
    }

    override suspend fun delete(genre: Genre) {
        localGenreDataSource.delete(genre)
    }

    override suspend fun update(genre: Genre) {
        localGenreDataSource.update(genre)
    }

}