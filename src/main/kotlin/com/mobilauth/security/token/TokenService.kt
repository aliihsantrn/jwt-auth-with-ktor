package com.mobilauth.security.token

//token oluşturma mantığını soyutlama
interface TokenService {
    fun generate(
        config: TokenConfig,
        vararg claims: TokenClaim
    ): String
}