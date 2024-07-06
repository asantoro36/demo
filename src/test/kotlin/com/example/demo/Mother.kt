package com.example.demo

import com.example.demo.entities.Account
import com.example.demo.entities.Transaction
import com.example.demo.requests.CreateTransactionRequest

object Mother {

    fun aCreateTransactionRequest(
        withSourceAccountId: Int = 1,
        withDestinationAccountId: Int = 2,
        withAmount: Double = 100.0
    ) = CreateTransactionRequest(
        sourceAccountId = withSourceAccountId,
        destinationAccountId = withDestinationAccountId,
        amount = withAmount
    )

    fun aTransaction(
        withId: Int? = null,
        withSourceAccount: Account = anAccount(),
        withDestinationAccount: Account = anAccount(),
        withAmount: Double = 10000.0
    )= Transaction(
       withId, withSourceAccount, withDestinationAccount, withAmount
    )

    fun anAccount(
        withId: Int? = null,
        withBankName: String = "Santander",
        withCountry: String = "Argentina",
        withBalance: Double = 5000.0
    ) = Account(withId, withBankName, withCountry, withBalance)
}