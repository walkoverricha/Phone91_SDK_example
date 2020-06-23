package com.phone91.sdk.data

import android.content.Context
//import com.android.billingclient.api.Purchase
//import com.google.android.gms.maps.model.LatLng
import com.google.gson.JsonObject
import com.phone91.sdk.data.database.AppDBManager
import com.phone91.sdk.data.preference.AppPreferenceManager
import com.phone91.sdk.data.remote.RemoteDataManager
import com.phone91.sdk.model.ChannelObject
import io.reactivex.Completable
import io.reactivex.Flowable
//import com.mbaka.ui.model.ProfileObject
import io.reactivex.Observable
import io.reactivex.Single
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


class AppDataManager
@Inject
constructor(private var context: Context,
            private var remoteDataManager: RemoteDataManager,
            preferenceManager: AppPreferenceManager,
            dBManager: AppDBManager) : DataManager {

    private var appPreferenceManager: AppPreferenceManager = preferenceManager
    private var appDBManager: AppDBManager = dBManager



    override fun getWidgetToken(): String? {
        return appPreferenceManager.getWidgetToken()
    }

    override fun setWidgetToken(widgetToken: String) {
        appPreferenceManager.setWidgetToken(widgetToken)
    }


    override fun getChannel(): String? {
        return appPreferenceManager.getChannel()
    }

    override fun setChannel(channel: String) {
        appPreferenceManager.setChannel(channel)
    }


    override fun getUUID(): String? {
        return appPreferenceManager.getUUID()
    }

    override fun setUUID(UUID: String) {
        appPreferenceManager.setUUID(UUID)
    }




    override fun getPubkey(): String? {
        return appPreferenceManager.getPubkey()
    }

    override fun setPubkey(pubkey: String) {
        appPreferenceManager.setPubkey(pubkey)
    }

    override fun getSubkey(): String? {
        return appPreferenceManager.getSubkey()
    }

    override fun setSubkey(subkey: String) {
        appPreferenceManager.setSubkey(subkey)
    }

    override fun getAuth(): String? {
        return appPreferenceManager.getAuth()
    }

    override fun setAuth(auth: String) {
        appPreferenceManager.setAuth(auth)
    }

    override fun getChannelByTeamID(teamid: String): Single<ChannelObject> {
        return appDBManager.getChannelByTeamID(teamid)
    }

    override fun getNameByChannel(channel: String): Single<ChannelObject> {
        return appDBManager.getNameByChannel(channel)
    }

override fun isFavouriteShow(teamid: String): Single<ChannelObject> {
        return appDBManager.getChannelByTeamID(teamid)
    }

    override fun insertChannel(channelObject: ChannelObject) : Completable {
       return appDBManager.insertChannel(channelObject)
    }


    override fun getClientDetail(): Observable<Response<JsonObject>> =
        remoteDataManager.getClientDetail()

    override fun getChannelDetail(channel: String?, name : String?, number : String?, mail:String?, unique_id : String, team_id : String?): Observable<Response<JsonObject>> =
        remoteDataManager.getChannelDetail(channel,name,number,mail,unique_id,team_id)

    override fun getPubnubDetail(): Observable<Response<JsonObject>> =
        remoteDataManager.getPubnubDetail()

    override fun sendImage(imagePath :String): Observable<Response<JsonObject>> =
        remoteDataManager.sendImage(imagePath)



//    fun addPost(
//        caption: String,location: String, image: String,publish_date: String,file_type:String,device_type:String,tagged_ids:String
//    ): Observable<Response<JsonObject>> =
//            remoteDataManager.addPost(caption, location, image,publish_date,file_type,device_type,tagged_ids)







}



