package com.account.Account.Service.dto

import com.account.Account.Service.dto.TransferMoneyRequest

data class TransferMoneyEvent (
    var status: String? = null,
    var message: String? = null,
    var req: TransferMoneyRequest? = null
)