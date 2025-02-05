package com.example.demo.entities

import com.example.demo.responses.GetAccountResponse
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Account(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id:  Int? = null,
    val bank: String,
    val country: String,
    val balance: Double,
) {
    fun toDTO() =
        GetAccountResponse(id, bank, country, balance)

    override fun toString() ="($bank - $country)"


}
