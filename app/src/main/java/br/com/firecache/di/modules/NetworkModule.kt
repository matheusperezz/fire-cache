package br.com.firecache.di.modules

import android.content.Context
import br.com.firecache.utils.NetworkUtils
import br.com.firecache.utils.ip
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val BASE_URL = ip

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideNetworkUtils(@ApplicationContext context: Context): NetworkUtils {
        return NetworkUtils(context)
    }

}