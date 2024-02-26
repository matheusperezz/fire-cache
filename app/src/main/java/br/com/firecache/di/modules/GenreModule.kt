package br.com.firecache.di.modules

import br.com.firecache.data.dao.GenreDao
import br.com.firecache.data.database.AppDatabase
import br.com.firecache.data.datasource.LocalGenreDataSource
import br.com.firecache.data.datasource.LocalGenreDataSourceImpl
import br.com.firecache.data.repositories.GenreRepositoryImpl
import br.com.firecache.domain.repository.GenreRepository
import br.com.firecache.domain.usecases.GenreUseCase
import br.com.firecache.domain.usecases.GenreUseCaseImpl
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
    fun provideLocalDataSource(genreDao: GenreDao): LocalGenreDataSource = LocalGenreDataSourceImpl(genreDao)

    @Provides
    @Singleton
    fun provideGenreRepository(localGenreDataSource: LocalGenreDataSource): GenreRepository =
        GenreRepositoryImpl(localGenreDataSource)

    @Provides
    @Singleton
    fun provideGenreUseCase(genreRepository: GenreRepository): GenreUseCase = GenreUseCaseImpl(genreRepository)
}