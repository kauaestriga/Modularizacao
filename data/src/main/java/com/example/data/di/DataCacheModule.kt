package com.example.data.di

import com.example.data.local.database.ProductsDatabase
import com.example.data.local.datasource.ProductCacheDataSource
import com.example.data.local.datasource.ProductCacheDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheDataModule = module {

    single { ProductsDatabase.createDatabase(androidContext()) }

    factory<ProductCacheDataSource> { ProductCacheDataSourceImpl(productDao = get())  }

}