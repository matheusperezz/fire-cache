package br.com.firecache.di.modules

import br.com.firecache.data.dao.BookDao
import br.com.firecache.data.database.AppDatabase
import br.com.firecache.data.datasource.LocalBookDataSource
import br.com.firecache.data.datasource.LocalBookDataSourceImpl
import br.com.firecache.data.repositories.BookRepositoryImpl
import br.com.firecache.domain.repository.BookRepository
import br.com.firecache.domain.usecases.BookUseCase
import br.com.firecache.domain.usecases.BookUseCaseImpl
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
    fun provideLocalDataSource(bookDao: BookDao): LocalBookDataSource =
        LocalBookDataSourceImpl(bookDao)

    @Provides
    @Singleton
    fun provideBookRepository(localBookDataSource: LocalBookDataSource): BookRepository =
        BookRepositoryImpl(localBookDataSource)

    @Provides
    @Singleton
    fun provideBookUseCase(bookRepository: BookRepository): BookUseCase =
        BookUseCaseImpl(bookRepository)
}