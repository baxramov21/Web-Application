package com.example.webapplication.data.db

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.webapplication.data.db.converters.DataConverter

@androidx.room.Database(entities = [WebItemDbModel::class], version = 2, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {

    abstract fun getDao(): MainDao

    companion object {

        private val LOCK = Any()
        private var INSTANCE: MainDatabase? = null
        private const val DB_NAME = "universe_item.db"

        fun getInstance(application: Application): MainDatabase {
            INSTANCE?.let {
                return it
            }

            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }

                val db = Room.databaseBuilder(
                    application,
                    MainDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = db
                return db
            }
        }
    }
}