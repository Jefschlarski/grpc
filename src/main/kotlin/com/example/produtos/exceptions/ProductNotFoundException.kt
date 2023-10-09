package com.example.produtos.exceptions

import io.grpc.Status

class ProductNotFoundException(private val productId: Long) : BaseBusinessException() {
    override fun errorMessage(): String {
        return "O id $productId não foi encontrado"
    }

    override fun statusCode(): Status.Code {
       return Status.Code.NOT_FOUND
    }

}