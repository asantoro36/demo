package com.example.demo.responses

data class GetAccountResponse(
    val id:  Int? = null,
    val bank: String,
    val country: String,
    val balance: Double,
)
