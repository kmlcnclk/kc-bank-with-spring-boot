package com.account.Account.Service.repository

import com.account.Account.Service.domain.Account
import org.springframework.data.mongodb.repository.MongoRepository

interface AccountRepository : MongoRepository<Account, String>