package com.account.Account.Service.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "accounts")
data class Account(
    @Id
    val id: String? = null,
    val currency: String,
    val iban: String,
    val balance: Double,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val userId: String
)
