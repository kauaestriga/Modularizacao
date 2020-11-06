package com.example.data.remote.datasource

import com.example.data.remote.api.ProductAPI
import com.example.data.remote.mapper.ProductPayloadMapper
import com.example.domain.entity.Product
import io.reactivex.Single

class ProductRemoteDataSourceImpl(private val productAPI: ProductAPI) : ProductRemoteDataSource {

    override fun getProducts(): Single<List<Product>> {
        return productAPI.getProducts().map { ProductPayloadMapper.map(it) }
    }
}