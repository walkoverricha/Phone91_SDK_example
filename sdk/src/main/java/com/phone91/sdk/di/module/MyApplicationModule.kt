package com.phone91.sdk.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.phone91.sdk.data.AppDataManager
import com.phone91.sdk.data.DataManager
import com.phone91.sdk.data.database.ChannelDao
import com.phone91.sdk.data.database.SDKDatabase
import com.phone91.sdk.data.preference.AppPreferenceManager
import com.phone91.sdk.data.preference.PreferenceSource
import com.phone91.sdk.di.qualifiers.PreferenceInfo
import com.phone91.sdk.utils.Constants.PREF_NAME
//import com.mbaka.ui.com.phone91.sdk.utils.Constants.PREF_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ActivityViewModelModule::class])
class MyApplicationModule {

    @Provides
    @Singleton
    internal fun bindContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun getPreferenceSource(appPreferenceManager: AppPreferenceManager):
            PreferenceSource = appPreferenceManager

    @Provides
    @Singleton
    internal fun provideAppDataManger(appDataManager: AppDataManager): DataManager = appDataManager


    @Provides
    @PreferenceInfo
    internal fun providePreferenceName(): String {
        return PREF_NAME
    }

    @Provides
    @Singleton
    fun provideSDKDatabase(app: Application): SDKDatabase = Room.databaseBuilder(app,
        SDKDatabase::class.java, "phone91sdk.db").build()

    @Provides
    @Singleton
    fun provideChannelDao(database: SDKDatabase): ChannelDao = database.channelDao()

}