package com.demo.albums.data.api

import android.content.Context
import com.demo.albums.application.NestConfig
import com.demo.albums.data.repository.MainRepository
import com.demo.albums.local.AppDatabase
import com.demo.albums.local.dao.DaoAccess
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*Setting up the App Module class-
* SingletonComponent is passed in @InstallIn here because we want the AppModule to be available for us
* for application scope.
* @Singleton- a single instance over the whole app.
* */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val URL = NestConfig.ENDPOINT

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideDataSource(apiService: ApiService) = ApiHelper(apiService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideDAO(db: AppDatabase) = db.daoAccess()

    @Singleton
    @Provides
    fun provideRepository(
        apiHelper: ApiHelper,
        localDataSource: DaoAccess
    ) =
        MainRepository(apiHelper, localDataSource)
}
