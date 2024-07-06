package com.example.demo.services

import com.example.demo.entities.Transaction
import jdk.jfr.DataAmount
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Service

@Service
class TransactionService {
    @Autowired
    val transactionTaskExecutor: ThreadPoolTaskExecutor? = null

    @Async("transactionTaskExecutor")
    fun executeAsyncTask(sourceAccountId: Int, destinationAccountId:Int, amount: Double) {
        println("Executing async task on thread: " + Thread.currentThread().name)
    }
}