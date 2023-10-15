package com.example.produtos.controller

import com.example.produtos.Empty
import com.example.produtos.RequestById
import com.example.produtos.ProductServiceGrpc
import com.example.produtos.ProductServiceRequest
import com.example.produtos.ProductServiceUpdateRequest
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@MicronautTest
class ProductControllerTestIT(

    private val productServiceBlockingStub : ProductServiceGrpc.ProductServiceBlockingStub
) {
    @Test
    fun `when ProductServiceGrpc create method is call with valid data a sucess is returned`(){
        val request = ProductServiceRequest.newBuilder()
            .setName("teste")
            .setPrice(12.00)
            .setStock(11)
            .build()

        val response = productServiceBlockingStub.create(request)

        Assertions.assertEquals(3, response.id)
        Assertions.assertEquals("teste", response.name)
    }

    @Test
    fun `when ProductServiceGrpc create method is call with invalid data a AlreadyExistsException is returned`(){
        val request = ProductServiceRequest.newBuilder()
                .setName("Product A")
                .setPrice(10.99)
                .setStock(50)
                .build()
        val response = Assertions.assertThrows(StatusRuntimeException::class.java){
            productServiceBlockingStub.create(request)
        }
        val description = "produto ${request.name} já cadastrado"
        Assertions.assertEquals(Status.ALREADY_EXISTS.code, response.status.code)
        Assertions.assertEquals(description, response.status.description)
    }
    @Test
    fun `when ProductServiceGrpc findById method is call with valid id a sucess is returned`(){
        val request = RequestById.newBuilder()
                .setId(1)
                .build()
        val response = productServiceBlockingStub.findById(request)
        Assertions.assertEquals(1, response.id)
        Assertions.assertEquals("Product A", response.name)
    }

    @Test
    fun `when ProductServiceGrpc findById method is call with invalid id a ProductNotFound is returned`(){
        val request = RequestById.newBuilder()
                .setId(999)
                .build()
        val response = Assertions.assertThrows(StatusRuntimeException::class.java){
            productServiceBlockingStub.findById(request)
        }
        val description = "O id ${request.id} não foi encontrado"
        Assertions.assertEquals(Status.NOT_FOUND.code, response.status.code)
        Assertions.assertEquals(description, response.status.description)
    }

    @Test
    fun `when ProductServiceGrpc update method is call with valid data a sucess is returned`(){
        val request = ProductServiceUpdateRequest.newBuilder()
                .setId(2L)
                .setName("teste2")
                .setPrice(12.00)
                .setStock(11)
                .build()

        val response = productServiceBlockingStub.update(request)

        Assertions.assertEquals(2, response.id)
        Assertions.assertEquals("teste2", response.name)
    }

    @Test
    fun `when ProductServiceGrpc update method is call with invalid data a AlreadyExistsException is returned`(){
        val request = ProductServiceUpdateRequest.newBuilder()
                .setId(2L)
                .setName("Product A")
                .setPrice(10.99)
                .setStock(50)
                .build()
        val response = Assertions.assertThrows(StatusRuntimeException::class.java){
            productServiceBlockingStub.update(request)
        }
        val description = "produto ${request.name} já cadastrado"
        Assertions.assertEquals(Status.ALREADY_EXISTS.code, response.status.code)
        Assertions.assertEquals(description, response.status.description)
    }

    @Test
    fun `when ProductServiceGrpc update method is call with invalid id a ProductNotFound is returned`(){
        val request = ProductServiceUpdateRequest.newBuilder()
                .setId(10L)
                .setName("Product w")
                .setPrice(10.99)
                .setStock(50)
                .build()
        val response = Assertions.assertThrows(StatusRuntimeException::class.java){
            productServiceBlockingStub.update(request)
        }
        val description = "O id ${request.id} não foi encontrado"
        Assertions.assertEquals(Status.NOT_FOUND.code, response.status.code)
        Assertions.assertEquals(description, response.status.description)
    }

    @Test
    fun `when ProductServiceGrpc delete method is call with valid id a sucess is returned`(){
        val request = RequestById.newBuilder()
                .setId(2L)
                .build()
        Assertions.assertDoesNotThrow{
            productServiceBlockingStub.delete(request)
        }
    }
    @Test
    fun `when ProductServiceGrpc delete method is call with invalid id a ProductNotFound is returned`(){
        val request = RequestById.newBuilder()
                .setId(10L)
                .build()
        val response = Assertions.assertThrows(StatusRuntimeException::class.java){
            productServiceBlockingStub.delete(request)
        }
        val description = "O id ${request.id} não foi encontrado"
        Assertions.assertEquals(Status.NOT_FOUND.code, response.status.code)
        Assertions.assertEquals(description, response.status.description)
    }

    @Test
    fun `when ProductServiceGrpc findAll method is call a list of ProductRes is returned`(){
        val request = Empty.newBuilder().build()
        val productListResponse = productServiceBlockingStub.findAll(request)


        Assertions.assertEquals("Product A",  productListResponse.getProducts(0).name)
        Assertions.assertEquals("Product B",  productListResponse.getProducts(1).name)
    }
}