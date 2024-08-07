package example.com.data.repository

import example.com.domain.Consumer

interface ConsumerRepository {
    fun getAllConsumers(): List<Consumer>
    fun getConsumerById(id: Int): Consumer?
    fun addConsumer(consumer: Consumer): Consumer
    fun updateConsumer(consumer: Consumer): Boolean
    fun deleteConsumer(id: Int): Boolean
}