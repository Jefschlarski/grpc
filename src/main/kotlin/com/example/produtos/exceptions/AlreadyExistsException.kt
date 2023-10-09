package com.example.produtos.exceptions

import io.grpc.Status

class AlreadyExistsException(private val productName: String) : BaseBusinessException() {
    override fun errorMessage(): String {
        return "produto $productName já cadastrado"
    }

    override fun statusCode(): Status.Code {
       return Status.Code.ALREADY_EXISTS
    }

}