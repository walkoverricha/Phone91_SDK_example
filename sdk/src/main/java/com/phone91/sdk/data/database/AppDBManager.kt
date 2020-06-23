package com.phone91.sdk.data.database

import android.content.Context
import com.phone91.sdk.model.ChannelObject
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class AppDBManager
@Inject
constructor(private val context: Context, private var channelDao: ChannelDao):DBManager{

//    private var channelDao: ChannelDao = SDKDatabase.getInstance(context).channelDao()


    override public fun getChannelByTeamID(teamid: String): Single<ChannelObject> {

        return channelDao.getDetailById(teamid)
    }
    override public fun getNameByChannel(channel: String): Single<ChannelObject> {

        return channelDao.getNAmeByChannel(channel)
    }

    override public fun isFavouriteShow(teamid: String): Single<ChannelObject> {

        return channelDao.getDetailById(teamid)
    }

    override public fun insertChannel(channelObject: ChannelObject): Completable {
        return channelDao.insertChannel(channelObject)
    }


}