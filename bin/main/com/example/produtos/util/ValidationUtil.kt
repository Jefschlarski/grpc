package com.example.produtos.util

import com.example.produtos.ProductServiceRequest

class ValidationUtil {
    companion object{
        fun validatePayload(payload: ProductServiceRequest? ): ProductServiceRequest{
            payload?.let {
                if (it.name.isNullOrBlank())
                    throw IllegalArgumentException("Nome não pode ser nulo ou vazio")
                if (it.price.isNaN())
                    throw IllegalArgumentException("Preço precisa de um valor valido")
                return it
            }
            throw IllegalArgumentException()
        }
    }
}