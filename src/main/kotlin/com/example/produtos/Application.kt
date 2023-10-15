package com.example.produtos

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
		.args(*args)
		.packages("com.example.produtos")
		.start()
}

