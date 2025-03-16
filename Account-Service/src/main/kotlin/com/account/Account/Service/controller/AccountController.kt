package com.account.Account.Service.controller


import com.account.Account.Service.dto.TransferMoneyRequest
import com.account.Account.Service.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class AccountController(private val  accountService: AccountService) {

    @PostMapping("/transfer-money")
    @ResponseStatus(HttpStatus.OK)
    fun transferMoney(@RequestBody req: TransferMoneyRequest) = accountService.transferMoney(req)
}