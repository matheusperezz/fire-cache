package br.com.firecache.data.repositories

import br.com.firecache.data.dao.BookDao
import br.com.firecache.data.models.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface BookRepository {
    fun fetchAll(): Flow<List<Book>>
    suspend fun insert(book: Book)

    suspend fun delete(book: Book)
}

@Singleton
class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
): BookRepository {
    override fun fetchAll(): Flow<List<Book>> = bookDao.fetchAll()

    override suspend fun insert(book: Book) {
        bookDao.insert(book)
    }

    override suspend fun delete(book: Book) {
        bookDao.delete(book)
    }

}