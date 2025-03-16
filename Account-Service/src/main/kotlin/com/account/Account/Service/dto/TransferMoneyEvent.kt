package com.account.Account.Service.dto

data class TransferMoneyEvent (
    var status: String? = null,
    var message: String? = null,
    var req: TransferMoneyRequest? = null
)