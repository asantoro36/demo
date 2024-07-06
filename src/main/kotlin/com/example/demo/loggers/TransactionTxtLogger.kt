package com.example.demo.loggers

import org.springframework.stereotype.Component

@Component
class TransactionTxtLogger: TransactionLogger {
    override fun log(reason: String, status: String) {

    }
}