package com.example.produtos.resources

import com.example.produtos.ProductServiceGrpc
import com.example.produtos.ProductServiceRequest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@MicronautTest
class ProductResourcesTestIT(
    private val productServiceBlockingStub : ProductServiceGrpc.ProductServiceBlockingStub
) {
    @Test
    fun `when ProductServiceGrpc create method is call with valid data a sucess is returned`(){
        val request = ProductServiceRequest.newBuilder()
            .setName("product name")
            .setPrice(10.99)
            .setStock(50)
            .build()
        val response = productServiceBlockingStub.create(request)
        Assertions.assertEquals(1, response.id)
        Assertions.assertEquals("product name", response.name)
    }
}