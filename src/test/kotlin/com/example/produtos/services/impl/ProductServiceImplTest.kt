package com.example.produtos.services.impl

import com.example.produtos.domain.Product
import com.example.produtos.dto.ProductReq
import com.example.produtos.dto.ProductUpdateReq
import com.example.produtos.exceptions.AlreadyExistsException
import com.example.produtos.exceptions.ProductNotFoundException
import com.example.produtos.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

internal class ProductServiceImplTest {
    private val productRepository = Mockito.mock(ProductRepository::class.java)
    private val productServices = ProductServiceImpl(productRepository)


    //TESTE DO RETORNO DO METODO CREATE CASO SUCESSO
    @Test
    fun `when create method is call with valid data a ProductRes is returned`(){
        val productIn = Product(id = null, name = "product name", price = 10.00, stock = 5)
        val productOut = Product(id = 1, name = "product name", price = 10.00, stock = 5)

        Mockito.`when`(productRepository.save(productIn)).thenReturn(productOut)

        val productReq = ProductReq(name = "product name", price = 10.00, stock = 5)
        val productRes = productServices.create(productReq)
        Assertions.assertEquals(productReq.name, productRes.name )
    }

    //TESTE DE EXCEÇÕES DO METODO CREATE
    @Test
    fun `when create method is call with duplicated product-name throws AlreadyExistsException`(){
        val productIn = Product(id = null, name = "product name", price = 10.00, stock = 5)
        val productOut = Product(id = 1, name = "product name", price = 10.00, stock = 5)

        Mockito.`when`(productRepository.findByNameIgnoreCase(productIn.name)).thenReturn(productOut)

        val productReq = ProductReq(name = "product name", price = 10.00, stock = 5)
        Assertions.assertThrowsExactly(AlreadyExistsException::class.java){productServices.create(productReq)}
    }
    //TESTE DO RETORNO DO METODO FINDYBYID
    @Test
    fun `when findById method is call with existing id a ProductRes is returned`(){
        val productIn = 1L
        val productOut = Product(id = 1, name = "product name", price = 10.00, stock = 5)

        Mockito.`when`(productRepository.findById(productIn)).thenReturn(Optional.of(productOut))

        val productRes = productServices.findById(productIn)
        Assertions.assertEquals(productIn, productRes.id)
        Assertions.assertEquals("product name", productRes.name)
    }

    //TESTE DE EXCEÇÃO DO METODO FINDBYID
    @Test
    fun `when findById method is call with invalid id throws ProductNotFoundException`(){
        val id = 1L
        Assertions.assertThrowsExactly(ProductNotFoundException::class.java){productServices.findById(id)}
    }

    //TESTE DE EXCEÇÕES DO METODO UPDATE VALIDAÇAO DO NOME
    @Test
    fun `when update method is call with duplicated product-name throws AlreadyExistsException`(){
        val productIn = Product(id = null, name = "product name", price = 10.00, stock = 5)
        val productOut = Product(id = 1, name = "product name", price = 10.00, stock = 5)

        Mockito.`when`(productRepository.findByNameIgnoreCase(productIn.name)).thenReturn(productOut)

        val productUpdateReq = ProductUpdateReq(id=1 ,name = "product name", price = 10.00, stock = 5)
        Assertions.assertThrowsExactly(AlreadyExistsException::class.java){productServices.update(productUpdateReq)}
    }
    //TESTE DE EXCEÇÃO DO METODO UPDATE VALIDAÇÃO DO ID
    @Test
    fun `when update method is call with invalid id throws ProductNotFoundException`(){
        val productUpdateReq = ProductUpdateReq(id=1 ,name = "product name", price = 10.00, stock = 5)
        Assertions.assertThrowsExactly(ProductNotFoundException::class.java){productServices.update(productUpdateReq)}
    }

    //TESTE DO RETORNO DO METODO UPDATE CASO SUCESSO
    @Test
    fun `when update method is call with valid data a ProductRes is returned`(){
        val productIn = Product(id = 1, name = "update name", price = 10.00, stock = 5)
        val productOut = Product(id = 1, name = "product name", price = 10.00, stock = 5)

        Mockito.`when`(productRepository.findById(productIn.id)).thenReturn(Optional.of(productOut))
        Mockito.`when`(productRepository.update(productIn)).thenReturn(productIn)

        val productUpdateReq = ProductUpdateReq(id= 1, name = "update name", price = 10.00, stock = 5)
        val productRes = productServices.update(productUpdateReq)
        Assertions.assertEquals(productUpdateReq.name, productRes.name )
    }
}