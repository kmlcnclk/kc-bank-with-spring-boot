package com.account.Account.Service.config

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {
    @Value("\${rabbitmq.queue.transferMoney.name}")
    private val transferMoneyQueue: String? = null

    @Value("\${rabbitmq.exchange.transferMoney.name}")
    private val transferMoneyExchange: String? = null

    @Value("\${rabbitmq.binding.routing.transferMoney.key}")
    private val transferMoneyRoutingKey: String? = null

    @Value("\${rabbitmq.queue.notification.name}")
    private val notificationQueue: String? = null

    @Value("\${rabbitmq.exchange.notification.name}")
    private val notificationExchange: String? = null

    @Value("\${rabbitmq.binding.routing.notification.key}")
    private val notificationRoutingKey: String? = null

    @Bean
    fun transferMoneyQueue(): Queue {
        return Queue(transferMoneyQueue)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(transferMoneyExchange)
    }

    @Bean
    fun binding(): Binding {
        return BindingBuilder
            .bind(transferMoneyQueue())
            .to(exchange())
            .with(transferMoneyRoutingKey)
    }

    @Bean
    fun notificationQueue(): Queue {
        return Queue(notificationQueue)
    }

    @Bean
    fun notificationExchange(): TopicExchange {
        return TopicExchange(notificationExchange)
    }

    @Bean
    fun notificationBinding(): Binding {
        return BindingBuilder
            .bind(notificationQueue())
            .to(notificationExchange())
            .with(notificationRoutingKey)
    }

    @Bean
    fun converter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun amqpTemplate(connectionFactory: ConnectionFactory): AmqpTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = converter()
        return rabbitTemplate
    }
}