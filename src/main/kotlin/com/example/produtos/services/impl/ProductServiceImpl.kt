package com.example.produtos.services.impl

import com.example.produtos.domain.Product
import com.example.produtos.dto.ProductReq
import com.example.produtos.dto.ProductRes
import com.example.produtos.dto.ProductUpdateReq
import com.example.produtos.exceptions.AlreadyExistsException
import com.example.produtos.exceptions.ProductNotFoundException
import com.example.produtos.repository.ProductRepository
import com.example.produtos.services.ProductServices
import com.example.produtos.util.toDomain
import com.example.produtos.util.toProductRes
import jakarta.inject.Singleton

@Singleton
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductServices {
    override fun create(req: ProductReq): ProductRes {
        verifyName(req.name)
        val productResponse = productRepository.save(req.toDomain())
        return productResponse.toProductRes()
    }

    override fun findById(id: Long): ProductRes {
        val findById= productRepository.findById(id)
        findById.orElseThrow {ProductNotFoundException(id)}
        return findById.get().toProductRes()
    }

    override fun update(req: ProductUpdateReq): ProductRes {
        verifyName(req.name)
       val product = productRepository.findById(req.id).orElseThrow{ProductNotFoundException(req.id)}
      val copy = product.copy(
              name = req.name,
              price = req.price,
              stock = req.stock)
        return productRepository.update(copy).toProductRes()
    }

    private fun verifyName(name: String){
        productRepository.findByNameIgnoreCase(name)?.let {
            throw AlreadyExistsException(name)
        }
    }
}