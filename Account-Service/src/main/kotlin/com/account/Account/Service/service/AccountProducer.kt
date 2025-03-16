package com.account.Account.Service.service

import com.account.Account.Service.dto.NotificationEvent
import com.account.Account.Service.dto.TransferMoneyEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class AccountProducer(
    private val rabbitTemplate: RabbitTemplate,
) {

    private val LOGGER: Logger = LoggerFactory.getLogger(AccountService::class.java)

    // Transfer Money exchange
    @Value("\${rabbitmq.exchange.transferMoney.name}")
    private val transferMoneyExchange: String? = null

    // Transfer Money routing key
    @Value("\${rabbitmq.binding.routing.transferMoney.key}")
    private val transferMoneyRoutingKey: String? = null


    // Notification exchange
    @Value("\${rabbitmq.exchange.notification.name}")
    private val notificationExchange: String? = null

    // Notification routing key
    @Value("\${rabbitmq.binding.routing.notification.key}")
    private val notificationRoutingKey: String? = null


    fun sendMessage(transferMoneyEvent: TransferMoneyEvent) {
        rabbitTemplate.convertAndSend(transferMoneyExchange!!, transferMoneyRoutingKey!!, transferMoneyEvent)

        LOGGER.info(String.format("Transfer Money event sent to RabbitMQ => %s", transferMoneyEvent.toString()))
    }

    @RabbitListener(queues = ["\${rabbitmq.queue.transferMoney.name}"])
    fun consumeTransferMoney(event: TransferMoneyEvent) {
        LOGGER.info(String.format("Transfer Money event received => %s", event.message))

        // TODO: make transfer money process

        val notificationEvent = NotificationEvent(
            status = "Completed",
            message = "Money successfully transferred!",
            from = event.req?.fromIBAN,
            to = event.req?.toIBAN,
        )

        rabbitTemplate.convertAndSend(notificationExchange!!, notificationRoutingKey!!, notificationEvent)

        LOGGER.info(String.format("Notification Event sent for Transfer Money Process => %s", notificationEvent.message))
    }
}