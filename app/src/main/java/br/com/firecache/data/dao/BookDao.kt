package br.com.firecache.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.firecache.data.models.BookCardModel

@Dao
interface BookDao {

    @Query("SELECT * FROM BookCardModel")
    suspend fun fetchAll(): List<BookCardModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(book: BookCardModel)

}