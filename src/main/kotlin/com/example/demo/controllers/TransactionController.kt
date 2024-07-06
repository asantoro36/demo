package com.example.demo.controllers

import com.example.demo.services.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/transactions")
class TransactionController(
    @Autowired private val transactionService: TransactionService
){

    @PostMapping
    fun postTransaction(): ResponseEntity<Unit> {
        transactionService.executeAsyncTask()
        return ResponseEntity.ok().build()
    }

}