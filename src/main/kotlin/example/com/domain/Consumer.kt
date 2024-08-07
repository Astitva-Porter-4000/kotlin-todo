package example.com.domain

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
data class Consumer(val id: Int, val name: String, val type: String)

object Consumers : IntIdTable() {
    val name = varchar("name", 50)
    val type = varchar("type", 50)
}
