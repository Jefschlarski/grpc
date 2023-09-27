package com.example.produtos.controller
import com.example.produtos.ProductServiceGrpc
import com.example.produtos.ProductServiceRequest
import com.example.produtos.ProductServiceResponse
import com.example.produtos.dto.ProductReq
import com.example.produtos.dto.ProductRes
import com.example.produtos.services.ProductServices
import com.example.produtos.util.ValidationUtil
import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService

@GrpcService
class productController (private val productService : ProductServices) : ProductServiceGrpc.ProductServiceImplBase() {
    override fun create(request: ProductServiceRequest?, responseObserver: StreamObserver<ProductServiceResponse>?) {
        val payload = ValidationUtil.validatePayload(request)
        val productReq = ProductReq(name = payload!!.name, price = payload.price, stock = payload.stock)
        val productRes = productService.create(productReq)
        val productResponse = ProductServiceResponse.newBuilder()
            .setId(productRes.id)
            .setName(productRes.name)
            .setPrice(productRes.price)
            .setStock(productRes.stock)
            .build()

        responseObserver?.onNext(productResponse)
        responseObserver?.onCompleted()

    }
}