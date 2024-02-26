package br.com.firecache.data.repositories

import br.com.firecache.data.dao.BookDao
import br.com.firecache.data.datasource.LocalBookDataSource
import br.com.firecache.domain.entities.Book
import br.com.firecache.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BookRepositoryImpl @Inject constructor(
    private val localBookDataSource: LocalBookDataSource
): BookRepository {
    override fun fetchAll(): Flow<List<Book>> = localBookDataSource.fetchAll()
    override fun fetchById(bookId: String): Flow<Book?> {
        return localBookDataSource.fetchById(bookId)
    }

    override suspend fun insert(book: Book) {
        localBookDataSource.insert(book)
    }

    override suspend fun delete(book: Book) {
        localBookDataSource.delete(book)
    }

    override suspend fun update(book: Book) {
        localBookDataSource.update(book)
    }

}