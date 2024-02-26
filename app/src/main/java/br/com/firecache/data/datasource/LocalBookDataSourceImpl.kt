package br.com.firecache.data.datasource

import br.com.firecache.data.dao.BookDao
import br.com.firecache.domain.entities.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalBookDataSourceImpl @Inject constructor(
    private val bookDao: BookDao
): LocalBookDataSource {
    override fun fetchAll(): Flow<List<Book>> {
        return bookDao.fetchAll()
    }

    override fun fetchById(bookId: String): Flow<Book?> {
        return bookDao.fetchById(bookId)
    }

    override suspend fun insert(book: Book) {
        bookDao.insert(book)
    }

    override suspend fun delete(book: Book) {
        bookDao.delete(book)
    }

    override suspend fun update(book: Book) {
        bookDao.update(book)
    }

}