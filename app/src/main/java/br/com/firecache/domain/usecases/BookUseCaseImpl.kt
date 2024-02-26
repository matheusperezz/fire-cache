package br.com.firecache.domain.usecases

import br.com.firecache.domain.entities.Book
import br.com.firecache.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookUseCaseImpl @Inject constructor(
    private val bookRepository: BookRepository
) : BookUseCase {
    override fun fetchAll(): Flow<List<Book>> {
        return bookRepository.fetchAll()
    }

    override fun fetchById(bookId: String): Flow<Book?> {
        return bookRepository.fetchById(bookId)
    }

    override suspend fun insert(book: Book) {
        bookRepository.insert(book)
    }

    override suspend fun delete(book: Book) {
        bookRepository.delete(book)
    }

    override suspend fun update(book: Book) {
        bookRepository.update(book)
    }

}