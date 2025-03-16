package com.account.Account.Service.domain

import java.time.LocalDateTime

data class Account (
    val id: String,
    val currency: String,
    val iban: String,
    val balance: Double,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val userId: String
)