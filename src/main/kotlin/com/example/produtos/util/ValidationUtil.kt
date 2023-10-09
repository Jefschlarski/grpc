package com.example.produtos.util

import com.example.produtos.ProductServiceRequest
import com.example.produtos.ProductServiceUpdateRequest
import com.example.produtos.exceptions.InvalidArgumentException

class ValidationUtil {
    companion object{
        fun validatePayload(payload: ProductServiceRequest? ): ProductServiceRequest{
            payload?.let {
                if (it.name.isNullOrBlank())
                    throw InvalidArgumentException("nome")
                if (it.price.isNaN()||it.price < 0)
                    throw InvalidArgumentException("preço")
                return it
            }
            throw InvalidArgumentException("Payload")
        }

        fun validateUpdatePayload(payload: ProductServiceUpdateRequest? ): ProductServiceUpdateRequest{
            payload?.let {
                if (it.id <=0L)
                    throw InvalidArgumentException("id")
                if (it.name.isNullOrBlank())
                    throw InvalidArgumentException("nome")
                if (it.price.isNaN()||it.price < 0)
                    throw InvalidArgumentException("preço")
                return it
            }
            throw InvalidArgumentException("Payload")
        }
    }
}