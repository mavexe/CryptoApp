package com.damirmustafin.cryptocurrency.model.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.damirmustafin.cryptocurrency.viewmodel.Api.pojo.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase protected constructor(): RoomDatabase() {

    abstract fun coinPriceInfoDao():CoinPriceInfoDao

    companion object{
        private var DB: AppDatabase? = null
        private const val DB_NAME = "Main.db"
        private val LOCK = Any()
        fun getInstance(context:Context): AppDatabase {
            synchronized(LOCK) {
                DB?.let { return it }
                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
                DB = instance
                return instance
            }
        }
    }
}