package com.example.produtos.controller

import com.example.produtos.*
import com.example.produtos.dto.ProductReq
import com.example.produtos.dto.ProductUpdateReq
import com.example.produtos.services.ProductServices
import com.example.produtos.util.ValidationUtil
import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService

@GrpcService
class productController(private val productService: ProductServices) : ProductServiceGrpc.ProductServiceImplBase() {
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

    override fun findById(request: RequestById?, responseObserver: StreamObserver<ProductServiceResponse>?) {

        val productRes = productService.findById(request!!.id)
        val productResponse = ProductServiceResponse.newBuilder()
                .setId(productRes.id)
                .setName(productRes.name)
                .setPrice(productRes.price)
                .setStock(productRes.stock)
                .build()

        responseObserver?.onNext(productResponse)
        responseObserver?.onCompleted()

    }

    override fun update(request: ProductServiceUpdateRequest?, responseObserver: StreamObserver<ProductServiceResponse>?) {

        val payload = ValidationUtil.validateUpdatePayload(request)
        val productReq = ProductUpdateReq(id = payload.id, name = payload.name, price = payload.price, stock = payload.stock)
        val productRes = productService.update(productReq)
        val productResponse = ProductServiceResponse.newBuilder()
                .setId(productRes.id)
                .setName(productRes.name)
                .setPrice(productRes.price)
                .setStock(productRes.stock)
                .build()

        responseObserver?.onNext(productResponse)
        responseObserver?.onCompleted()

    }

    override fun delete(request: RequestById?, responseObserver: StreamObserver<Empty>?) {

        productService.delete(request!!.id)
        responseObserver?.onNext(Empty.newBuilder().build())
        responseObserver?.onCompleted()

    }

    override fun findAll(request: Empty?, responseObserver: StreamObserver<ProductsList>?) {

        val productResList = productService.findAll()
        val productServiceResponseList = productResList.map {
            ProductServiceResponse.newBuilder()
                    .setId(it.id)
                    .setName(it.name)
                    .setPrice(it.price)
                    .setStock(it.stock)
                    .build()
        }

        val response = ProductsList.newBuilder().addAllProducts(productServiceResponseList).build()
        responseObserver?.onNext(response)
        responseObserver?.onCompleted()

    }
}