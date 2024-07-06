package com.example.demo.services

import com.example.demo.entities.Account
import com.example.demo.repositories.TransactionRepository
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import kotlin.test.Ignore

class TransactionServiceShould {

    val accountService: AccountService = mock()
    val transactionRepository: TransactionRepository = mock()
    val transactionService: TransactionService = TransactionService(accountService, transactionRepository)


    @Test
    fun `save transactions`() {
        given(accountService.getAccountById(any())).willReturn(Account(1, "Santander", "Argentina", 400.0))

        transactionService.executeAsyncTask(1, 10, 500.0)

        verify(transactionRepository, times(1)).save(any())
    }

    @Test
    fun `do nothing when source account does not exist`() {
        val wrongAccount = 1
        given(accountService.getAccountById(wrongAccount)).willReturn(null)
        whenCallExecuteAsyncTask(withSourceAccount = wrongAccount)
        verify(transactionRepository, times(0)).save(any())
    }

    @Test
    fun `return status failed when destination account does not exist`() {
        val wrongAccount = 1
        given(accountService.getAccountById(wrongAccount)).willReturn(null)

        whenCallExecuteAsyncTask(withDestinationAccount = wrongAccount)
        verify(transactionRepository, times(0)).save(any())
    }

    private fun whenCallExecuteAsyncTask(withSourceAccount: Int = 10, withDestinationAccount: Int = 10) {
        transactionService.executeAsyncTask(withSourceAccount, withDestinationAccount, 500.0)
    }
}