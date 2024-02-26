package br.com.firecache.data.datasource

import br.com.firecache.domain.entities.Book
import kotlinx.coroutines.flow.Flow

interface LocalBookDataSource {
    fun fetchAll(): Flow<List<Book>>
    fun fetchById(bookId: String): Flow<Book?>
    suspend fun insert(book: Book)
    suspend fun delete(book: Book)
    suspend fun update(book: Book)
}