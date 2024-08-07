package example.com

import example.com.plugins.consumerRoutes
import example.com.Service.ConsumerService
import di.DaggerAppComponent
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

fun main() {

    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    val appComponent = DaggerAppComponent.create()
    val consumerService = ConsumerService(appComponent.provideConsumerRepository())
    routing {
        consumerRoutes(consumerService)
    }
}