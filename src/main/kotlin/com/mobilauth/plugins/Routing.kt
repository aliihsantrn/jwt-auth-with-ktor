package com.mobilauth.plugins

import com.mobilauth.authenticate
import com.mobilauth.data.User.UserDataSource
import com.mobilauth.getSecretInfo
import com.mobilauth.security.hashing.HashingService
import com.mobilauth.security.token.TokenConfig
import com.mobilauth.security.token.TokenService
import com.mobilauth.signUp
import com.mobilauth.singIn
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    hashingService: HashingService,
    userDataSource: UserDataSource,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    routing {
        singIn(hashingService, userDataSource, tokenService, tokenConfig)
        signUp(hashingService, userDataSource)
        authenticate()
        getSecretInfo()
    }
}
