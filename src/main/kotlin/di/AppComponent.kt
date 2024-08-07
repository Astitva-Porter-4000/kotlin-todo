package di


import example.com.data.db.CustomDatabase
import example.com.data.repository.ConsumerRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface AppComponent {

    fun provideCustomDatabase(): CustomDatabase

    fun provideConsumerRepository(): ConsumerRepository
}
