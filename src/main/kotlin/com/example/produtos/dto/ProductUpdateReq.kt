package com.example.produtos.dto

data class ProductUpdateReq (
        val id: Long,
        val name: String,
        val price: Double,
        val stock: Int
)
