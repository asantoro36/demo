package com.example.demo.controllers

import com.example.demo.Mother.aCreateTransactionRequest
import com.example.demo.requests.CreateTransactionRequest
import com.example.demo.services.TransactionService
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus

class TransactionControllerShould {

    val transactionService: TransactionService = mock()
    val controller = TransactionController(transactionService)

    @Test
    fun `return 200 ok`() {
        val response = whenPostTransaction()
        assertThat(response).isNotNull
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `enqueue transactions and send correct transaction params`() {
        val expectedSourceAccountId = 5
        val expectedDestinationAccountId = 10
        val expectedAmount = 200.0

        whenPostTransaction(
            aCreateTransactionRequest(
                withSourceAccountId = expectedSourceAccountId,
                withDestinationAccountId = expectedDestinationAccountId,
                withAmount = expectedAmount
            )
        )
        thenExecuteAsyncTask(times = 1,
            expectedSourceAccountId,
            expectedDestinationAccountId,
            expectedAmount)
    }

    private fun whenPostTransaction(r: CreateTransactionRequest = aCreateTransactionRequest()) =
        controller.postTransaction(r)

    private fun thenExecuteAsyncTask(
        times: Int = 1,
        withSourceAccountId: Int,
        withDestinationAccountId: Int,
        withAmount: Double
    ) = verify(transactionService, times(times)).executeAsyncTask(withSourceAccountId, withDestinationAccountId, withAmount)
}