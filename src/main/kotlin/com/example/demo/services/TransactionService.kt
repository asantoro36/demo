package com.example.demo.services

import com.example.demo.entities.Transaction
import com.example.demo.loggers.TransactionLogger
import com.example.demo.repositories.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Service

@Service
class TransactionService(
    @Autowired private val accountService: AccountService,
    @Autowired private val transactionRepository: TransactionRepository,
    @Autowired private val transactionLogger: TransactionLogger
) {

    @Async("transactionTaskExecutor")
    fun executeAsyncTask(sourceAccountId: Int, destinationAccountId:Int, amount: Double) {
        println("Executing async task on thread: " + Thread.currentThread().name)
        try{
            val sourceAccount = accountService.getAccountById(sourceAccountId)
            val destinationAccount = accountService.getAccountById(destinationAccountId)

            if(sourceAccount!=null && destinationAccount!=null) {
                    processTransaction(Transaction(sourceAccount = sourceAccount, destinationAccount = destinationAccount, amount = amount))
                }
            }
        catch (e: Exception) {
            transactionLogger.log(e.message?:"Ocurrio un error", "Fail")
        }
    }

    fun processTransaction(newTransaction: Transaction) {
        val taxAmount = newTransaction.calculateTax()
        val transaction = transactionRepository.save(newTransaction)
        println("Se proceso la transaccion. Se envio $${transaction.amount} desde ${transaction.sourceAccount} hacia ${transaction.destinationAccount}. La comision fue de $${taxAmount}")
        transactionLogger.log(transaction.toString(), "Processed")
    }
}