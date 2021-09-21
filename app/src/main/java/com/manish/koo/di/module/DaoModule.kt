package com.manish.koo.di.module

import android.app.Application
import androidx.room.Room
import com.manish.koo.database.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaoModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): Database {
        return Room.databaseBuilder(
            application,
            Database::class.java, "database.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providePostDao(db: Database) = db.providePostDao()

}