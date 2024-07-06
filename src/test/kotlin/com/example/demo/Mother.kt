package com.example.demo

import com.example.demo.requests.CreateTransactionRequest

object Mother {

    fun aCreateTransactionRequest(
        withSourceAccountId: Int = 1,
        withDestinationAccountId: Int = 2,
        withAmount: Double = 100.0
    ) = CreateTransactionRequest(
        sourceAccountId = withSourceAccountId,
        destinationAccountId = withDestinationAccountId,
        amount = withAmount
    )
}