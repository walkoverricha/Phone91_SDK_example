package com.phone91.sdk.mvvm.dashboard.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.EmptyResultSetException
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.phone91.sdk.data.AppDataManager
//import com.phone91.sdk.model.QouteObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import com.google.gson.reflect.TypeToken
import com.phone91.sdk.model.ChannelObject


class HomeViewModel
@Inject
constructor(var appDataManager: AppDataManager) : ViewModel() {

    var error = MutableLiveData<String>()
    var latest_videos = MutableLiveData<ArrayList<String>>()
    var loading = MutableLiveData<Boolean>()
    var channelObjectData = MutableLiveData<ChannelObject>()
    var urlData = MutableLiveData<String>()
    var loggedout = MutableLiveData<Boolean>()

    private var compositeDisposable = CompositeDisposable()

    fun getNameByChannel(channel: String) {
        loading.value = true
        val loginDisposable = appDataManager.getNameByChannel(channel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                loading.value=false
                var channelObject: ChannelObject =it
//                if(channelObject==null) {
//                    channelObject = ChannelObject(-1,null,null,null,team_id,null,null,null,null/*,false,null*/)
////                    channelObject.
//
//                }
                channelObjectData.value=channelObject
            },
                { errors ->
//                    Log.e(TAG, "Unable to get username", errors)
                    loading.value=false

                        error.value=errors.message
                })
        compositeDisposable.add(loginDisposable)
    }


    fun shareFile(image: String) {
        loading.value = true
        val loginDisposable = appDataManager.sendImage(image)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
//                {"status":"Success","message":"attachment uploaded successfully",
//                    "body":{"attachment_url":"https://test.phone91.com/static/32/1587641131-IMG_20200423_141630.jpg"}}

                loading.value=false

               if( it.body()?.has("status")!! && it.body()?.get("status")?.asString.equals("Success")){
                   urlData.value= it.body()?.get("body")?.asJsonObject?.get("attachment_url")?.asString
               }
//                var channelObject: ChannelObject =it
//                if(channelObject==null) {
//                    channelObject = ChannelObject(-1,null,null,null,team_id,null,null,null,null/*,false,null*/)
////                    channelObject.
//
//                }
//                channelObjectData.value=channelObject
            },
                { errors ->
                    //                    Log.e(TAG, "Unable to get username", errors)
                    loading.value=false

                    error.value=errors.message
                })
        compositeDisposable.add(loginDisposable)
    }
}
