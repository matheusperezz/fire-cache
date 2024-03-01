package br.com.firecache.domain.usecases

import br.com.firecache.domain.entities.Genre
import kotlinx.coroutines.flow.Flow

interface GenreUseCase {
    fun fetchAll(): Flow<List<Genre>>
    fun fetchById(genreId: String): Flow<Genre?>
    suspend fun insert(genre: Genre)
    suspend fun delete(genre: Genre)
    suspend fun update(genre: Genre)
    suspend fun syndData()
}