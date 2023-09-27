package com.example.produtos.services.impl

import com.example.produtos.domain.Product
import com.example.produtos.dto.ProductReq
import com.example.produtos.dto.ProductRes
import com.example.produtos.repository.ProductRepository
import com.example.produtos.services.ProductServices
import com.example.produtos.util.toDomain
import com.example.produtos.util.toProductRes
import jakarta.inject.Singleton

@Singleton
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductServices {
    override fun create(req: ProductReq): ProductRes {
        val productResponse = productRepository.save(req.toDomain())
        return productResponse.toProductRes()
    }
}