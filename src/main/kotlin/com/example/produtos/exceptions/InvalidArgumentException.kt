package com.example.produtos.exceptions

import io.grpc.Status

class InvalidArgumentException(private val argumentName: String) : BaseBusinessException() {
    override fun errorMessage(): String {
        return "o argumento $argumentName é invalido"
    }

    override fun statusCode(): Status.Code {
       return Status.Code.INVALID_ARGUMENT
    }

}