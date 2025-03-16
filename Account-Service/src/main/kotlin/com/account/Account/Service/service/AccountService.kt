package com.account.Account.Service.service

import com.account.Account.Service.dto.TransferMoneyRequest
import com.account.Account.Service.dto.TransferMoneyEvent
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountProducer: AccountProducer,
) {

    fun transferMoney(req: TransferMoneyRequest): String {
        //TODO: make some checks for the transfer

        val transferMoneyEvent = TransferMoneyEvent(
            status = "PENDING",
            message = "Transfer Money is in pending status",
            req = req,
        )

        accountProducer.sendMessage(transferMoneyEvent)
        return "Your request is being processed!"
    }
}