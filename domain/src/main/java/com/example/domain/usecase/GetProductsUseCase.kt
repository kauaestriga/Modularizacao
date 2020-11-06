package com.example.domain.usecase

import com.example.domain.entity.Product
import com.example.domain.repository.ProductRepository
import io.reactivex.Scheduler
import io.reactivex.Single

class GetProductsUseCase (
    private val productRepository: ProductRepository,
    private val scheduler: Scheduler
) {
    fun execute(forceUpdate: Boolean) : Single<List<Product>> {
        return productRepository.getProducts(forceUpdate)
            .subscribeOn(scheduler)
    }
}
