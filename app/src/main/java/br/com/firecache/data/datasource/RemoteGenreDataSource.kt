package br.com.firecache.data.datasource

import br.com.firecache.domain.entities.CreateGenre
import br.com.firecache.domain.entities.Genre
import kotlinx.coroutines.flow.Flow

interface RemoteGenreDataSource {
    fun fetchAll(): Flow<List<Genre>>
    fun fetchById(id: String): Flow<Genre>
    suspend fun insert(genre: CreateGenre)
}