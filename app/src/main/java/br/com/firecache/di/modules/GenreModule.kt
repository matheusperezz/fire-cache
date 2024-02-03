package br.com.firecache.di.modules

import br.com.firecache.data.dao.GenreDao
import br.com.firecache.data.database.AppDatabase
import br.com.firecache.data.repositories.GenreRepository
import br.com.firecache.data.repositories.GenreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GenreModule {

    @Provides
    @Singleton
    fun provideGenreDao(database: AppDatabase): GenreDao = database.genreDao()

    @Provides
    @Singleton
    fun provideGenreRepository(genreDao: GenreDao): GenreRepository = GenreRepositoryImpl(genreDao)

}