package com.example.demo.entities

import jakarta.persistence.*

private val internationalTax = 5.0
private val nationalAndAnotherBankTax = 1.0
@Entity
data class Transaction(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Int? = null,
    @ManyToOne
    @JoinColumn(name = "source_account_id")
    val sourceAccount: Account,
    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    val destinationAccount: Account,
    val amount: Double,
) {
    fun calculateTax() = amount * (getTax() / 100.0)

    fun getTax(): Double {
        if(sourceAccount.country != destinationAccount.country) {
            return internationalTax
        }
        if(destinationAccount.bank != sourceAccount.bank) {
            return nationalAndAnotherBankTax
        }
        return 0.0;
    }

    override fun toString(): String {
        return "Transaction(id=$id, sourceAccount=$sourceAccount, destinationAccount=$destinationAccount, amount=$amount)"
    }

}