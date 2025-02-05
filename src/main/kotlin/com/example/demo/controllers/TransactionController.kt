package com.example.demo.controllers

import com.example.demo.repositories.AccountRepository
import com.example.demo.requests.CreateTransactionRequest
import com.example.demo.services.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/transactions")
class TransactionController(
    @Autowired private val transactionService: TransactionService
){

    @PostMapping
    fun postTransaction(@RequestBody request: CreateTransactionRequest): ResponseEntity<Unit> {
        transactionService.executeAsyncTask(request.sourceAccountId, request.destinationAccountId, request.amount)
        return ResponseEntity.ok().build()
    }
}