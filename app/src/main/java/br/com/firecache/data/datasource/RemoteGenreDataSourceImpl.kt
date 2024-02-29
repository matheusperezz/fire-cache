package br.com.firecache.data.datasource

import br.com.firecache.data.services.GenreService
import br.com.firecache.domain.entities.CreateGenre
import br.com.firecache.domain.entities.Genre
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import javax.inject.Inject

class RemoteGenreDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): RemoteGenreDataSource {

    override fun fetchAll(): Flow<List<Genre>> {
        return retrofit.create(GenreService::class.java).fetchAllGenres()
    }

    override fun fetchById(id: String): Flow<Genre> {
        return retrofit.create(GenreService::class.java).fetchGenreById(id)
    }

    override suspend fun insert(genre: CreateGenre) {
        retrofit.create(GenreService::class.java).saveGenre(genre)
    }

}