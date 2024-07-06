package com.example.demo.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Service

@Service
class TransactionService {
    @Autowired
    val transactionTaskExecutor: ThreadPoolTaskExecutor? = null

    @Async("transactionTaskExecutor")
    fun executeAsyncTask() {
        println("Executing async task on thread: " + Thread.currentThread().name)
    }
}