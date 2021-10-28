package com.paulvarry.cartoparty.data_source.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.paulvarry.cartoparty.repository.points.db.PointEntity
import com.paulvarry.cartoparty.repository.points.db.PointDao

@Database(entities = [PointEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pointsDao(): PointDao

    companion object {
        fun get(context: Context) = Room.databaseBuilder(
            context, AppDatabase::class.java, "database.db"
        ).build()
    }
}
