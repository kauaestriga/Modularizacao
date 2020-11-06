package com.example.data.di

import com.example.data.remote.api.ProductAPI
import com.example.data.remote.datasource.ProductRemoteDataSource
import com.example.data.remote.datasource.ProductRemoteDataSourceImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {

    factory { providesOkHttpClient() }

    single {
        createWebService<ProductAPI>(
            okHttpClient = get(),
            url = "https://www.mocky.io"
        )
    }

    factory<ProductRemoteDataSource> {
        ProductRemoteDataSourceImpl(productAPI = get())
    }
}

fun providesOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    url: String
): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(url)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}