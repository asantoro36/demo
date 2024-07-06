package com.example.demo.configurations

import com.example.demo.entities.Account
import com.example.demo.repositories.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class  DataConfig(@Autowired private val accountRepository: AccountRepository) {
    @Bean
    fun initializeDatabase(): CommandLineRunner {
        return CommandLineRunner {
            accountRepository.save(Account(1, "Santander", "Argentina", 150000.0))
            accountRepository.save(Account(2, "Santander", "Argentina", 2150000.0))
            accountRepository.save(Account(3, "BBVA", "Argentina", 50000.0))
        }
    }
}