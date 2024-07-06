package com.example.demo.services

import com.example.demo.entities.Account
import com.example.demo.repositories.AccountRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountService(@Autowired val repository: AccountRepository) {
    fun getAccountById(accountId: Int): Account {
        try {
            return repository.getReferenceById(accountId)
        } catch (e: EntityNotFoundException) {
            throw RuntimeException("Account $accountId not found")
        }
    }
}
