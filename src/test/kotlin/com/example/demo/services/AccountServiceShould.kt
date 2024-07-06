package com.example.demo.services

import com.example.demo.entities.Account
import com.example.demo.repositories.AccountRepository

import org.assertj.core.api.Assertions.*
import org.mockito.kotlin.*
import org.junit.jupiter.api.Test

class AccountServiceShould {

    val accountRepository: AccountRepository = mock()
    val service = AccountService(accountRepository)

    @Test
    fun `return null when account does not exist`() {
        val account = service.getAccountById(5);
        assertThat(account).isNull()
    }

    @Test
    fun `return account by id`() {
        val savedAccount = Account(10, "ICBC", "Argentina", 100.0)
        given(accountRepository.getReferenceById(10)).willReturn(savedAccount)

        val account = service.getAccountById(savedAccount.id!!);

        assertThat(account).isNotNull()
        assertThat(savedAccount.id).isEqualTo(account!!.id)
        assertThat(savedAccount.bank).isEqualTo(account.bank)
        assertThat(savedAccount.country).isEqualTo(account.country)
        assertThat(savedAccount.balance).isEqualTo(account.balance)
    }

}