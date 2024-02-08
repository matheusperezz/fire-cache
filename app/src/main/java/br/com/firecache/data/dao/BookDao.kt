package br.com.firecache.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.firecache.data.models.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM Book")
    fun fetchAll(): Flow<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Delete
    suspend fun delete(book: Book)
}