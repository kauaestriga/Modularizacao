package com.example.data.repository

import com.example.data.local.datasource.ProductCacheDataSource
import com.example.data.remote.datasource.ProductRemoteDataSource
import com.example.domain.entity.Product
import com.example.domain.repository.ProductRepository
import io.reactivex.Single

class ProductRepositoryImpl (
    private val productsCacheDataSource: ProductCacheDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource
): ProductRepository {

    override fun getProducts(forceUpdate: Boolean): Single<List<Product>> {
        return if (forceUpdate)
            getProductsRemote(forceUpdate)
        else
            productsCacheDataSource.getProducts()
                .flatMap { products ->
                    when {
                        products.isEmpty() -> getProductsRemote(false)
                        else -> Single.just(products)
                    }
                }
    }

    private fun getProductsRemote(isUpdate: Boolean): Single<List<Product>> {
        return productRemoteDataSource.getProducts()
            .flatMap { products ->
                if (isUpdate)
                    productsCacheDataSource.updateData(products)
                else
                    productsCacheDataSource.insertData(products)
                Single.just(products)
            }
    }
}