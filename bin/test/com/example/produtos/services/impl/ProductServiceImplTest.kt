package com.example.produtos.services.impl

import com.example.produtos.domain.Product
import com.example.produtos.dto.ProductReq
import com.example.produtos.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

internal class ProductServiceImplTest {
    private val productRepository = Mockito.mock(ProductRepository::class.java)
    private val productServices = ProductServiceImpl(productRepository)

    @Test
    fun `when create method is call with valid data a ProductRes is returned`(){
        val productIn = Product(id = null, name = "product name", price = 10.00, stock = 5)
        val productOut = Product(id = 1, name = "product name", price = 10.00, stock = 5)

        Mockito.`when`(productRepository.save(productIn)).thenReturn(productOut)

        val productReq = ProductReq(name = "product name", price = 10.00, stock = 5)
        val productRes = productServices.create(productReq)
        Assertions.assertEquals(productReq.name, productRes.name )
    }
}