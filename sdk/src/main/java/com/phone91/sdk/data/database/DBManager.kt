package com.phone91.sdk.data.database

import android.content.Context
import com.phone91.sdk.model.ChannelObject
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

interface DBManager{

//    private var channelDao: ChannelDao = SDKDatabase.getInstance(context).channelDao()


    public fun getChannelByTeamID(teamid: String): Single<ChannelObject>
    public fun getNameByChannel(channel: String): Single<ChannelObject>
    public fun isFavouriteShow(teamid: String): Single<ChannelObject>

    public fun insertChannel(channelObject: ChannelObject) : Completable



}