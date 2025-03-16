package com.notification.Notification.Service.service

import com.notification.Notification.Service.dto.NotificationEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class NotificationConsumer(
    private val notificationService: NotificationService,
) {

    private val LOGGER: Logger = LoggerFactory.getLogger(NotificationService::class.java)

    @RabbitListener(queues = ["\${rabbitmq.queue.notification.name}"])
    fun consumeNotificationEvent(event: NotificationEvent) {
        LOGGER.info(String.format("Notification Event received message from => %s", event.message))

        notificationService.sendNotification(event)

        LOGGER.info(String.format("Notification Event sent for => %s", event.message))
    }
}