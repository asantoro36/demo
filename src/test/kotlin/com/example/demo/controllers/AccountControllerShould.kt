package com.example.demo.controllers

import com.example.demo.Mother.anAccount
import com.example.demo.entities.Account
import com.example.demo.responses.GetAccountResponse
import com.example.demo.services.AccountService
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.springframework.http.HttpStatus

class AccountControllerShould {

    private val accountService: AccountService = mock()
    private val controller = AccountController(accountService);

    @Test
    fun `return not found when account does not exist`() {
        given(accountService.getAccountById(any())).willReturn(null)

        val response = controller.getAccount(5)

        assertThat(response).isNotNull
        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    fun `return account when account exists`() {
        given(accountService.getAccountById(any())).willReturn(anAccount(withId = 100))

        val response = controller.getAccount(5)

        assertThat(response).isNotNull
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isNotNull
        val savedAccount = response.body as GetAccountResponse
        assertThat(savedAccount.id).isEqualTo(100)
    }
}