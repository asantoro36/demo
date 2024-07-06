package com.example.demo.entities

import com.example.demo.Mother.aTransaction
import com.example.demo.Mother.anAccount
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TransactionTest {

    @Test
    fun `When is a national transaction and the same bank, then taxes doest not be applied`() {
        val transaction = aTransaction(
            withSourceAccount = anAccount(withCountry = "Argentina", withBankName = "Santander"),
            withDestinationAccount = anAccount(withCountry = "Argentina", withBankName = "Santander"),
            withAmount = 1000.0
        )

        val taxAmount = transaction.calculateTax()

        Assertions.assertThat(taxAmount).isEqualTo(0.0)
    }

    @Test
    fun `When is an international transaction, then a tax is applied`() {
        val transaction = aTransaction(
            withSourceAccount = anAccount(withCountry = "EEUU"),
            withDestinationAccount = anAccount(withCountry = "Argentina"),
            withAmount = 1000.0
        )

        val taxAmount = transaction.calculateTax()

        Assertions.assertThat(taxAmount).isEqualTo(50.0)
    }

    @Test
    fun `When is a national transaction and different bank, then a tax is applied`() {
        val transaction = aTransaction(
            withSourceAccount = anAccount(withCountry = "Argentina", withBankName = "Santander"),
            withDestinationAccount = anAccount(withCountry = "Argentina", withBankName = "ICBC"),
            withAmount = 1000.0
        )

        val taxAmount = transaction.calculateTax()

        Assertions.assertThat(taxAmount).isEqualTo(10.0)
    }
}