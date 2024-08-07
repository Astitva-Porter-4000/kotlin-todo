package example.com.data.repository
import example.com.data.db.CustomDatabase
import example.com.domain.Consumer
import example.com.domain.Consumers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject

class ConsumerRepositoryImpl @Inject constructor(private val customDatabase: CustomDatabase) : ConsumerRepository {
    override fun getAllConsumers(): List<Consumer> = transaction(customDatabase.database) {
        Consumers.selectAll().map { Consumer(it[Consumers.id].value, it[Consumers.name], it[Consumers.type]) }
    }

    override fun getConsumerById(id: Int): Consumer? = transaction(customDatabase.database) {
        Consumers.select { Consumers.id eq id }
            .mapNotNull { Consumer(it[Consumers.id].value, it[Consumers.name], it[Consumers.type]) }.singleOrNull()
    }

    override fun addConsumer(consumer: Consumer): Consumer = transaction(customDatabase.database) {
        Consumers.insertAndGetId {
            it[name] = consumer.name
            it[type] = consumer.type
        }.value.let { consumer.copy(id = it) }
    }

    override fun updateConsumer(consumer: Consumer): Boolean = transaction(customDatabase.database) {
        Consumers.update({ Consumers.id eq consumer.id }) {
            it[name] = consumer.name
            it[type] = consumer.type
        } > 0
    }

    override fun deleteConsumer(id: Int): Boolean = transaction(customDatabase.database) {
        Consumers.deleteWhere { Consumers.id eq id } > 0
    }
}
