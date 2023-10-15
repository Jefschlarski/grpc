package com.example.produtos.interceptor
import com.example.produtos.exceptions.BaseBusinessException
import io.grpc.ForwardingServerCallListener
import io.grpc.Metadata
import io.grpc.ServerCall


class ExceptionHandlerCallListener<ReqT, RespT> (
        private val serverCall:ServerCall<ReqT, RespT>?,
        private val metadata: Metadata?,
        delegate: ServerCall.Listener<ReqT>?
) : ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(delegate){
    override fun onReady() {
        try {
            super.onReady()
        }catch (ex:BaseBusinessException){
            serverCall?.close(ex.statusCode().toStatus().withDescription(ex.errorMessage()), metadata)
        }
    }

    override fun onHalfClose() {
        try {
            super.onHalfClose()
        }catch (ex:BaseBusinessException){
            serverCall?.close(ex.statusCode().toStatus().withDescription(ex.errorMessage()), metadata)
        }
    }
}
