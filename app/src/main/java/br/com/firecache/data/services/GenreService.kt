package br.com.firecache.data.services

import br.com.firecache.domain.entities.CreateGenre
import br.com.firecache.domain.entities.Genre
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val GENRE_RESOURCE = "genres"

interface GenreService {

    @GET(GENRE_RESOURCE)
    fun fetchAllGenres(): Flow<List<Genre>>

    @GET("$GENRE_RESOURCE/{id}")
    fun fetchGenreById(@Path("id") id: String): Flow<Genre>

    @POST(GENRE_RESOURCE)
    suspend fun saveGenre(@Body genre: Genre)
}