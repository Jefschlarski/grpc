package com.example.produtos.util

import com.example.produtos.domain.Product
import com.example.produtos.dto.ProductReq
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ProductConverterUtilTest {
    @Test
    fun `when toProductRest is call, should return a ProductRes with all data`(){
        val product = Product(id = 1, name = "product-name", price = 10.90, stock = 10)

        val productRes = product.toProductRes()

        Assertions.assertEquals(product.id, productRes.id)
        Assertions.assertEquals(product.name, productRes.name)
        Assertions.assertEquals(product.price, productRes.price)
        Assertions.assertEquals(product.stock, productRes.stock)
    }
    @Test
    fun `when toDomain is call, should return a Product with all data`(){
        val productReq = ProductReq(name = "product-name", price = 10.90, stock = 10)

        val product = productReq.toDomain()
        Assertions.assertEquals(null, product.id)
        Assertions.assertEquals(productReq.name, product.name)
        Assertions.assertEquals(productReq.price, product.price)
        Assertions.assertEquals(productReq.stock, product.stock)
    }
}