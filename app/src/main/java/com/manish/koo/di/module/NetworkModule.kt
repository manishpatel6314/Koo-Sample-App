package com.manish.koo.di.module

import com.manish.koo.helper.AppExecutors
import com.manish.koo.remoteUtils.LiveDataCallAdapterFactory
import com.manish.koo.remoteUtils.WebService
import com.manish.koo.remoteUtils.WebServiceHolder
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideExecutor() = AppExecutors()

    @Provides
    fun webServiceHolder() = WebServiceHolder.instance

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(150, TimeUnit.SECONDS)
        .readTimeout(150, TimeUnit.SECONDS)
        .writeTimeout(150, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original = chain.request()
            // add request headers
            val request = original.newBuilder()
                .header("User-Agent", System.getProperty("http.agent"))
                .build()
            chain.proceed(request)
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://gorest.co.in/public/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideApiWebservice(restAdapter: Retrofit): WebService {
        val webService = restAdapter.create(WebService::class.java)
        WebServiceHolder.instance.setAPIService(webService)
        return webService
    }
}