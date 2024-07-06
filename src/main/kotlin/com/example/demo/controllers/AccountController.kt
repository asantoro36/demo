package com.example.demo.controllers

import com.example.demo.services.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/accounts")
class AccountController(@Autowired private val accountService: AccountService) {

    @GetMapping("/{id}")
    fun getAccount(@PathVariable id: Int) : ResponseEntity<Any> {
        val account = accountService.getAccountById(id)
        if (account == null)
            return ResponseEntity.notFound().build()
        else
            return ResponseEntity.ok(account.toDTO())
    }
}