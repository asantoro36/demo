package com.example.demo.controllers

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
        val response = controller.postTransaction()
        assertThat(response).isNotNull
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `enqueue transactions`() {
        controller.postTransaction()
        verify(transactionService, times(1)).executeAsyncTask()
    }
}