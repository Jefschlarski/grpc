package com.example.produtos.util

import com.example.produtos.ProductServiceRequest
import com.example.produtos.ProductServiceUpdateRequest
import com.example.produtos.exceptions.InvalidArgumentException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class ValidationUtilTest {

    @Test
    fun `when validatePayload method is call with valid data, should not throw exception`(){
        val request = ProductServiceRequest.newBuilder()
                .setName("product name")
                .setPrice(10.99)
                .setStock(50)
                .build()
        Assertions.assertDoesNotThrow {
            ValidationUtil.validatePayload(request);
        }
    }
    @Test
    fun `when validateUpdatePayload method is call with valid data, should not throw exception`(){
        val request = ProductServiceUpdateRequest.newBuilder()
                .setId(1L)
                .setName("product name")
                .setPrice(10.99)
                .setStock(50)
                .build()
        Assertions.assertDoesNotThrow {
            ValidationUtil.validateUpdatePayload(request);
        }
    }
    @Test
    fun `when validatePayload method is call with invalid product name, should throw exception`(){
        val request = ProductServiceRequest.newBuilder()
                .setName("")
                .setPrice(10.99)
                .setStock(50)
                .build()
        Assertions.assertThrowsExactly(InvalidArgumentException::class.java) {
            ValidationUtil.validatePayload(request);
        }
    }

    @Test
    fun `when validateUpdatePayload method is call with invalid product name, should throw exception`(){
        val request = ProductServiceUpdateRequest.newBuilder()
                .setId(1L)
                .setName("")
                .setPrice(10.99)
                .setStock(50)
                .build()
        Assertions.assertThrowsExactly(InvalidArgumentException::class.java) {
            ValidationUtil.validateUpdatePayload(request);
        }
    }
    @Test
    fun `when validatePayload method is call with invalid price, should throw exception`(){
        val request = ProductServiceRequest.newBuilder()
                .setName("product name")
                .setPrice(-10.99)
                .setStock(50)
                .build()
        Assertions.assertThrowsExactly(InvalidArgumentException::class.java) {
            ValidationUtil.validatePayload(request);
        }
    }

    @Test
    fun `when validateUpdatePayload method is call with invalid price, should throw exception`(){
        val request = ProductServiceUpdateRequest.newBuilder()
                .setId(1L)
                .setName("Teste")
                .setPrice(-10.99)
                .setStock(50)
                .build()
        Assertions.assertThrowsExactly(InvalidArgumentException::class.java) {
            ValidationUtil.validateUpdatePayload(request);
        }
    }
    @Test
    fun `when validatePayload method is call with null payload, should throw exception`(){
        Assertions.assertThrowsExactly(InvalidArgumentException::class.java) {
            ValidationUtil.validatePayload(null);
        }
    }

    @Test
    fun `when validateUpdatePayload method is call with null payload, should throw exception`(){
        Assertions.assertThrowsExactly(InvalidArgumentException::class.java) {
            ValidationUtil.validateUpdatePayload(null);
        }
    }
}