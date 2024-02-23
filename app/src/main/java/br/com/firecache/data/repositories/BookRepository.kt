package br.com.firecache.data.repositories

import br.com.firecache.data.dao.BookDao
import br.com.firecache.data.models.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface BookRepository {
    fun fetchAll(): Flow<List<Book>>
    fun fetchById(bookId: String): Flow<Book?>
    suspend fun insert(book: Book)
    suspend fun delete(book: Book)
    suspend fun update(book: Book)
}

@Singleton
class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
): BookRepository {
    override fun fetchAll(): Flow<List<Book>> = bookDao.fetchAll()
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