package br.com.firecache.domain.usecases

import br.com.firecache.domain.entities.Genre
import br.com.firecache.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreUseCaseImpl @Inject constructor(
    private val genreRepository: GenreRepository
): GenreUseCase {
    override fun fetchAll(): Flow<List<Genre>> {
        return genreRepository.fetchAll()
    }

    override fun fetchById(genreId: String): Flow<Genre?> {
        return genreRepository.fetchById(genreId)
    }

    override suspend fun insert(genre: Genre) {
        genreRepository.insert(genre)
    }

    override suspend fun delete(genre: Genre) {
        genreRepository.delete(genre)
    }

    override suspend fun update(genre: Genre) {
        genreRepository.update(genre)
    }
}