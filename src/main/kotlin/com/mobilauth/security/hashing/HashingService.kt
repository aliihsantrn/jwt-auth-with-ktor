package com.mobilauth.security.hashing

interface HashingService {
    fun generateSaltedHash(value: String, saltLength: Int = 32): SaltedHash //saltedhash oluşturma ve getirme
    fun verify(value: String, saltedHash: SaltedHash): Boolean //saltedhash doğrulama
}