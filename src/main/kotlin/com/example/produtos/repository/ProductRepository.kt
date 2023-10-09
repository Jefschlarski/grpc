package com.example.produtos.repository

import com.example.produtos.domain.Product
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository


@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findByNameIgnoreCase(name:String): Product? //findByNameIgnoreCase vai procurar pelo sem diferenciar maiscula.
}