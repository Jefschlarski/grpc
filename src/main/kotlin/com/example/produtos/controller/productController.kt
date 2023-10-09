package com.example.produtos.controller
import com.example.produtos.FindByIdServiceRequest
import com.example.produtos.ProductServiceGrpc
import com.example.produtos.ProductServiceRequest
import com.example.produtos.ProductServiceResponse
import com.example.produtos.ProductServiceUpdateRequest
import com.example.produtos.dto.ProductReq
import com.example.produtos.dto.ProductRes
import com.example.produtos.dto.ProductUpdateReq
import com.example.produtos.exceptions.BaseBusinessException
import com.example.produtos.services.ProductServices
import com.example.produtos.util.ValidationUtil
import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService

@GrpcService
class productController (private val productService : ProductServices) : ProductServiceGrpc.ProductServiceImplBase() {
    override fun create(request: ProductServiceRequest?, responseObserver: StreamObserver<ProductServiceResponse>?) {
        try {
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

        } catch (ex: BaseBusinessException){
            responseObserver?.onError(ex.statusCode().toStatus().withDescription(ex.errorMessage()).asRuntimeException())
        }
    }

    override fun findById(request: FindByIdServiceRequest?, responseObserver: StreamObserver<ProductServiceResponse>?) {
        try {
            val productRes = productService.findById(request!!.id)
            val productResponse = ProductServiceResponse.newBuilder()
                    .setId(productRes.id)
                    .setName(productRes.name)
                    .setPrice(productRes.price)
                    .setStock(productRes.stock)
                    .build()

            responseObserver?.onNext(productResponse)
            responseObserver?.onCompleted()
        }catch (ex: BaseBusinessException){
            responseObserver?.onError(ex.statusCode().toStatus().withDescription(ex.errorMessage()).asRuntimeException())
        }

    }

    override fun update(request: ProductServiceUpdateRequest?,responseObserver: StreamObserver<ProductServiceResponse>?){
        try {
            val payload = ValidationUtil.validateUpdatePayload(request)
            val productReq = ProductUpdateReq(id=payload.id, name = payload.name, price = payload.price, stock = payload.stock)
            val productRes = productService.update(productReq)
            val productResponse = ProductServiceResponse.newBuilder()
                    .setId(productRes.id)
                    .setName(productRes.name)
                    .setPrice(productRes.price)
                    .setStock(productRes.stock)
                    .build()

            responseObserver?.onNext(productResponse)
            responseObserver?.onCompleted()
        } catch (ex: BaseBusinessException){
            responseObserver?.onError(ex.statusCode().toStatus().withDescription(ex.errorMessage()).asRuntimeException())
        }

    }
}