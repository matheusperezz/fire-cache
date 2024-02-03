package br.com.firecache.data.repositories

import br.com.firecache.data.dao.BookDao
import br.com.firecache.data.models.BookCardModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface BookRepository {
    suspend fun fetchAll(): Flow<List<BookCardModel>>
    suspend fun insert(book: BookCardModel)
}

@Singleton
class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
): BookRepository {
    override suspend fun fetchAll(): Flow<List<BookCardModel>> = bookDao.fetchAll()

    override suspend fun insert(book: BookCardModel) {
        bookDao.insert(book)
    }

}