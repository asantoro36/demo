package com.example.demo.services

import com.example.demo.entities.Transaction
import com.example.demo.repositories.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Service

@Service
class TransactionService(
    @Autowired private val accountService: AccountService,
    @Autowired private val transactionRepository: TransactionRepository) {

    @Async("transactionTaskExecutor")
    fun executeAsyncTask(sourceAccountId: Int, destinationAccountId:Int, amount: Double) {
        Thread.sleep(1000)
        println("Executing async task on thread: " + Thread.currentThread().name)

        val sourceAccount = accountService.getAccountById(sourceAccountId)
        val destinationAccount = accountService.getAccountById(destinationAccountId)

        if(sourceAccount!=null && destinationAccount!=null) {
            transactionRepository.save(Transaction(sourceAccount = sourceAccount, destinationAccount = destinationAccount, amount = amount))
        }
    }
}