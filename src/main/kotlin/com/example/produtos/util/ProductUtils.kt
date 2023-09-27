package com.example.produtos.util
import com.example.produtos.domain.Product
import com.example.produtos.dto.ProductReq
import com.example.produtos.dto.ProductRes



fun Product.toProductRes() : ProductRes{
    return ProductRes(
        id = id!!,
        name = name,
        price = price,
        stock = stock
    )
}



fun ProductReq.toDomain() : Product{
    return Product(
        id = null,
        name = name,
        price = price,
        stock = stock
    )
}