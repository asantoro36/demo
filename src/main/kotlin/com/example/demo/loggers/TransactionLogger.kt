package com.example.demo.loggers

import com.example.demo.entities.Transaction

interface TransactionLogger {
    fun log(reason: String, status: String)
}