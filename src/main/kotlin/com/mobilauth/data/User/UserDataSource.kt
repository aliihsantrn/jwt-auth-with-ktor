package com.mobilauth.data.User

interface UserDataSource {
    //Giriş yapılan username ile uyuşan kayıt varmı
    suspend fun getUserByUsername(username : String): User?
    //ilk defa giriş yapıyorsa kayıtlar uyusuyormu
    suspend fun insertUser(user : User): Boolean
}