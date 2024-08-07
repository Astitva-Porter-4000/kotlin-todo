package example.com.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import example.com.domain.Consumer
import example.com.Service.ConsumerService
import io.ktor.http.*
import io.ktor.server.request.*

fun Route.consumerRoutes(consumerService: ConsumerService) {
    route("consumers") {
        get {
            call.respond(consumerService.getAllConsumers())
        }
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            id?.let {
                val consumer = consumerService.getConsumerById(it)
                if (consumer != null) {
                    call.respond(consumer)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
        post {
            val consumer = call.receive<Consumer>()
            call.respond(HttpStatusCode.Created, consumerService.addConsumer(consumer))
        }
        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val consumer = call.receive<Consumer>()
            if (id != null && consumerService.updateConsumer(consumer.copy(id = id))) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id != null && consumerService.deleteConsumer(id)) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}

