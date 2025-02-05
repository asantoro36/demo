package com.example.demo.repositories

import com.example.demo.entities.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: JpaRepository<Account, Int> {
}