package br.com.firecache.data.datasource

import br.com.firecache.domain.entities.Genre
import kotlinx.coroutines.flow.Flow

interface LocalGenreDataSource {
    fun fetchAll(): Flow<List<Genre>>
    fun fetchById(genreId: String): Flow<Genre?>
    suspend fun insert(genre: Genre)
    suspend fun delete(genre: Genre)
    suspend fun update(genre: Genre)
}