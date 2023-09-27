package com.example.produtos.config

import com.example.produtos.ProductServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel

@Factory
class GrpcConfig {
    @Bean
    fun productServiceBean(
        @GrpcChannel("productservice")
        channel: ManagedChannel
    ): ProductServiceGrpc.ProductServiceBlockingStub{
    return ProductServiceGrpc.newBlockingStub(channel)
    }
}