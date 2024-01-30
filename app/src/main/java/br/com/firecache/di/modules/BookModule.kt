package br.com.firecache.di.modules

import br.com.firecache.data.dao.BookDao
import br.com.firecache.data.database.AppDatabase
import br.com.firecache.data.repositories.BookRepository
import br.com.firecache.data.repositories.BookRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BookModule {

    @Provides
    @Singleton
    fun provideBookDao(database: AppDatabase): BookDao = database.bookDao()

    @Provides
    @Singleton
    fun provideBookRepository(bookDao: BookDao): BookRepository = BookRepositoryImpl(bookDao)

}