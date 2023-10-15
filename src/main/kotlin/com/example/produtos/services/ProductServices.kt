package com.example.produtos.services

import com.example.produtos.dto.ProductReq
import com.example.produtos.dto.ProductRes
import com.example.produtos.dto.ProductUpdateReq

interface ProductServices {
    fun create(req: ProductReq): ProductRes
    fun findById(id: Long): ProductRes
    fun findAll(): List<ProductRes>
    fun update(req: ProductUpdateReq): ProductRes
    fun delete(id: Long)
}