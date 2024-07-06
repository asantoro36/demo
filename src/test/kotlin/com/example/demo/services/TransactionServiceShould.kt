package com.example.demo.services

import com.example.demo.Mother.aTransaction
import com.example.demo.Mother.anAccount
import com.example.demo.entities.Account
import com.example.demo.entities.Transaction
import com.example.demo.loggers.TransactionTxtLogger
import com.example.demo.repositories.TransactionRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class TransactionServiceShould {

    lateinit var accountService: AccountService
    lateinit var transactionRepository: TransactionRepository
    lateinit var transactionLogger: TransactionTxtLogger
    lateinit var transactionService: TransactionService

    @BeforeEach
    fun setup() {
        accountService = mock()
        transactionRepository = mock()
        transactionLogger = mock()

        transactionService = TransactionService(accountService, transactionRepository, transactionLogger)
    }

    @Test
    fun `save transactions`() {
        given(accountService.getAccountById(any())).willReturn(anAccount())
        given(transactionRepository.save(any<Transaction>())).willReturn(aTransaction(withId = 1))

        transactionService.executeAsyncTask(1, 10, 500.0)

        verify(transactionRepository, times(1)).save(any())
    }

    @Test
    fun `do nothing when source account does not exist`() {
        val wrongAccount = 1
        given(accountService.getAccountById(wrongAccount)).willThrow(RuntimeException("message"))
        whenCallExecuteAsyncTask(withSourceAccount = wrongAccount)
        verify(transactionRepository, times(0)).save(any())
        verify(transactionLogger, times(1)).log(any(), eq("Fail"))
    }

    @Test
    fun `return status failed when destination account does not exist`() {
        val wrongAccount = 1
        given(accountService.getAccountById(wrongAccount)).willThrow(RuntimeException("message"))

        whenCallExecuteAsyncTask(withDestinationAccount = wrongAccount)
        verify(transactionRepository, times(0)).save(any())
        verify(transactionLogger, times(1)).log(any(), eq("Fail"))
    }

    @Test
    fun `log transaction procesed status`() {
        given(accountService.getAccountById(any())).willReturn(anAccount())
        given(transactionRepository.save(any<Transaction>())).willReturn(aTransaction(withId = 1))

        whenCallExecuteAsyncTask()

        verify(transactionLogger, times(1)).log(any(), eq("Processed"))
    }

    @Test
    fun `log transaction with fail status`() {
        given(accountService.getAccountById(any())).willReturn(anAccount())
        given(transactionRepository.save(any<Transaction>())).willThrow(RuntimeException("Error"))

        whenCallExecuteAsyncTask()

        verify(transactionLogger, times(1)).log(any(), eq("Fail"))
    }


    private fun whenCallExecuteAsyncTask(withSourceAccount: Int = 10, withDestinationAccount: Int = 10) {
        transactionService.executeAsyncTask(withSourceAccount, withDestinationAccount, 500.0)
    }
}