package com.example.produtos.services

import com.example.produtos.dto.ProductReq
import com.example.produtos.dto.ProductRes

interface ProductServices {
    fun create(req: ProductReq): ProductRes
}