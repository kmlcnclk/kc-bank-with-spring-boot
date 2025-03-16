package com.account.Account.Service.service

import com.account.Account.Service.domain.Account
import com.account.Account.Service.dto.CreateAccountDto
import com.account.Account.Service.dto.TransferMoneyRequest
import com.account.Account.Service.dto.TransferMoneyEvent
import com.account.Account.Service.repository.AccountRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.Random

@Service
class AccountService(
    private val accountProducer: AccountProducer,
    private val accountRepository: AccountRepository,
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

    fun findById(id: String): Account {
        val a = accountRepository.findById(id)
        return a.get()
    }

    fun save(accountDto: CreateAccountDto): String {

        val account = Account(
            currency = accountDto.currency,
            iban = generateIban(),
            balance = 0.0,
            userId = accountDto.userId,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )

        accountRepository.save(account)

        return "Account successfully created!"
    }

    fun deleteById(id: String): String {
        accountRepository.deleteById(id)

        return "Account successfully deleted!"

    }

    private fun generateIban(countryCode: String = "TR", bankCode: String = "00061"): String {
        val random = Random()
        val accountNumber = (1..16).map { random.nextInt(10) }.joinToString("")

        val checkDigits = "00"
        return "$countryCode$checkDigits$bankCode$accountNumber"
    }

}