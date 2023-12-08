package ir.one_developer.batmanmovies.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.one_developer.batmanmovies.BuildConfig
import ir.one_developer.batmanmovies.data.repository.MovieRepository
import ir.one_developer.batmanmovies.data.repository.MovieRepositoryImpl
import ir.one_developer.batmanmovies.data.source.local.AppDatabase
import ir.one_developer.batmanmovies.data.source.remote.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor() = Interceptor { chain ->
        val request = chain.request()
        val requestUrl = request.url.newBuilder()
        requestUrl.addQueryParameter("apikey", "3e974fca")
        val newRequest = request.newBuilder().url(requestUrl.build())
        chain.proceed(newRequest.build())
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideConverter(
        moshi: Moshi
    ): Converter.Factory = MoshiConverterFactory.create(moshi)


    @Provides
    @Singleton
    fun provideHttpClient(
        interceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideApi(
        client: OkHttpClient,
        converter: Converter.Factory
    ): Api.V1 = Retrofit.Builder()
        .addConverterFactory(converter)
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .build()
        .create<Api.V1>()

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: Api.V1,
        appDatabase: AppDatabase
    ): MovieRepository = MovieRepositoryImpl(
        api = api,
        appDatabase = appDatabase
    )

}