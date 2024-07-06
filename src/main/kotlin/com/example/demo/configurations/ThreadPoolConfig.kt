package com.example.demo.configurations

import com.example.demo.controllers.TransactionController
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor


@Configuration
@EnableAsync
class ThreadPoolConfig {

    @Value("\${spring.task.execution.pool.core-size}")
    val corePoolSize: Int = 0
    @Value("\${spring.task.execution.pool.max-size}")
    val maxPoolSize: Int = 0
    @Value("\${spring.task.execution.pool.queue-capacity}")
    val queueCapacity: Int = 0

    @Bean
    fun transactionTaskExecutor(): ThreadPoolTaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = corePoolSize
        executor.maxPoolSize = maxPoolSize
        executor.queueCapacity = queueCapacity
        executor.setThreadNamePrefix("MyThreadPool-")
        executor.initialize()
        return executor
    }
}