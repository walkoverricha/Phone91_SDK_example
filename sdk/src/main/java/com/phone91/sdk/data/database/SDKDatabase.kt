package com.phone91.sdk.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.phone91.sdk.model.ChannelObject

@Database(entities = [ChannelObject::class], version = 1)
abstract class SDKDatabase : RoomDatabase() {

    abstract fun channelDao(): ChannelDao


    companion object {

        @Volatile private var INSTANCE: SDKDatabase? = null

        fun getInstance(context: Context): SDKDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    ).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                SDKDatabase::class.java, "phone91sdk.db")
                .build()
    }
}