package example.com.data.db

import example.com.domain.Consumers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File
import java.util.*


object CustomDatabase {
    lateinit var database: Database
    fun init() {
        val props = Properties()
        val propFile = File("src/main/resources/Application.properties")
        propFile.inputStream().use { input ->
            props.load(input)
        }
        val driver = props.getProperty("driver")
        val url = props.getProperty("url")
        val user = props.getProperty("user")
        val password = props.getProperty("password")

        database = Database.connect(url, driver, user, password)

        transaction(database) {
            SchemaUtils.create(Consumers)
        }
    }
}