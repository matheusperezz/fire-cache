package br.com.firecache.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.firecache.data.dao.BookDao
import br.com.firecache.data.dao.GenreDao
import br.com.firecache.data.models.BookCardModel
import br.com.firecache.data.models.Genre

@Database(entities = [BookCardModel::class, Genre::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun genreDao(): GenreDao
}