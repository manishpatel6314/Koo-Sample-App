package com.manish.koo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.manish.koo.post.PostDao
import com.manish.koo.post.PostData

@Database(
    entities = [
        PostData::class
    ], version = 1, exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun providePostDao() : PostDao
}

