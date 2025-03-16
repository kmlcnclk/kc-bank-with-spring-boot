package com.account.Account.Service.dto

data class TransferMoneyRequest (
    val amount: Double,
    val fromIBAN: String,
    val toIBAN: String,
)