package br.com.firecache.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.firecache.data.dao.BookDao
import br.com.firecache.data.models.BookCardModel

@Database(entities = [BookCardModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
}