package com.account.Account.Service.dto

data class NotificationEvent (
    var status: String? = null,
    var message: String? = null,
    var from: String? = null,
    var to: String? = null,
)