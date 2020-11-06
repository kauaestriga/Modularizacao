package com.example.domain.di

import com.example.domain.usecase.GetProductsUseCase
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        GetProductsUseCase(
            productRepository = get(),
            scheduler = Schedulers.io()
        )
    }
}

val domainModule = listOf(useCaseModule)