package di

import dagger.Module
import example.com.data.db.CustomDatabase
import example.com.data.repository.ConsumerRepository
import example.com.data.repository.ConsumerRepositoryImpl
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCustomDatabase(): CustomDatabase {
        val customDatabase = CustomDatabase
        customDatabase.init()
        return customDatabase
    }

    @Provides
    @Singleton
    fun provideConsumerRepository(customDatabase: CustomDatabase): ConsumerRepository {
        return ConsumerRepositoryImpl(customDatabase)
    }
}