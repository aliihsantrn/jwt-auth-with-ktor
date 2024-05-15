package com.mobilauth

import com.mobilauth.data.User.MongoUserDataSource
import com.mobilauth.plugins.*
import com.mobilauth.security.hashing.SHA256HashingService
import com.mobilauth.security.token.JWTTokenService
import com.mobilauth.security.token.TokenConfig
import io.ktor.server.application.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module(){
    val mongoPw = System.getenv("MONGO_PW")
    val dbName = "auth"
    val db = KMongo.createClient(
        connectionString = "mongodb+srv://aliihsan:$mongoPw@cluster0.5b64z9d.mongodb.net/$dbName?retryWrites=true&w=majority"
    )
        .coroutine
        .getDatabase(dbName)


    val userDataSource = MongoUserDataSource(db)
    val tokenService = JWTTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = System.getenv("JWT_SECRET")
    )

    val hashingService = SHA256HashingService()

    configureSecurity(tokenConfig)
    configureRouting(hashingService, userDataSource, tokenService, tokenConfig)
    configureSerialization()
    configureMonitoring()

}
