package com.example.demo.entities

import jakarta.persistence.*

@Entity
data class Transaction(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Int? = null,
    @OneToOne
    @JoinColumn(name = "source_account_id")
    val sourceAccount: Account,
    @OneToOne
    @JoinColumn(name = "destination_account_id")
    val destinationAccount: Account,
    val amount: Double,
)
