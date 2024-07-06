package com.example.demo.loggers

import org.springframework.stereotype.Component
import java.io.FileWriter
import java.io.IOException

@Component
class TransactionTxtLogger: TransactionLogger {
    override fun log(reason: String, status: String) {

        writeToFile("$status - $reason")
    }

    private fun writeToFile(logMessage: String) {
        val fileName = "transaction_log.txt"
        try {
            FileWriter(fileName, true).use { writer ->
                writer.write("$logMessage\n")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}