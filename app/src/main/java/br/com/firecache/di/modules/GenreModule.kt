package br.com.firecache.di.modules

import br.com.firecache.data.dao.GenreDao
import br.com.firecache.data.database.AppDatabase
import br.com.firecache.data.datasource.LocalGenreDataSource
import br.com.firecache.data.datasource.LocalGenreDataSourceImpl
import br.com.firecache.data.datasource.RemoteGenreDataSource
import br.com.firecache.data.datasource.RemoteGenreDataSourceImpl
import br.com.firecache.data.repositories.GenreRepositoryImpl
import br.com.firecache.domain.repository.GenreRepository
import br.com.firecache.domain.usecases.GenreUseCase
import br.com.firecache.domain.usecases.GenreUseCaseImpl
import br.com.firecache.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GenreModule {

    /* Local modules */
    @Provides
    @Singleton
    fun provideGenreDao(database: AppDatabase): GenreDao = database.genreDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(genreDao: GenreDao): LocalGenreDataSource =
        LocalGenreDataSourceImpl(genreDao)

    @Provides
    @Singleton
    fun provideGenreRepository(
        localGenreDataSource: LocalGenreDataSource,
        remoteGenreDataSource: RemoteGenreDataSource,
        networkUtils: NetworkUtils
    ): GenreRepository =
        GenreRepositoryImpl(localGenreDataSource, remoteGenreDataSource, networkUtils)

    @Provides
    @Singleton
    fun provideGenreUseCase(genreRepository: GenreRepository): GenreUseCase =
        GenreUseCaseImpl(genreRepository)

    /* Remote modules */
    @Provides
    @Singleton
    fun provideRemoteDataSource(retrofit: Retrofit): RemoteGenreDataSource = RemoteGenreDataSourceImpl(retrofit)

}