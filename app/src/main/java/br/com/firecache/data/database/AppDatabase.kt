package br.com.firecache.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.firecache.data.dao.BookDao
import br.com.firecache.data.dao.GenreDao
import br.com.firecache.data.models.Book
import br.com.firecache.data.models.Genre

@Database(entities = [Book::class, Genre::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun genreDao(): GenreDao
}