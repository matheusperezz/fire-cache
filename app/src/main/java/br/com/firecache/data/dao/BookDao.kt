package br.com.firecache.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.firecache.data.models.BookCardModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM BookCardModel")
    fun fetchAll(): Flow<List<BookCardModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: BookCardModel)

}