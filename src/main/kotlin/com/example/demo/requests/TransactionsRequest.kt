package com.example.demo.requests

data class CreateTransactionRequest(
    val sourceAccountId: Int,
    val destinationAccountId: Int,
    val amount: Double,
)


