package com.phone91.sdk.mvvm.dashboard

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.EmptyResultSetException
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.phone91.sdk.data.AppDataManager
import com.phone91.sdk.model.ChannelObject
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject


class DashboardActivityVM @Inject constructor(var appDataManager: AppDataManager) : ViewModel() {

    var error = MutableLiveData<String>()
    var loading = MutableLiveData<Boolean>()

    var widgetInfo = MutableLiveData<String>()
    var pubnubInfo = MutableLiveData<ArrayList<String>>()
    var channelInfo = MutableLiveData<ChannelObject>()
    var channelObjectData = MutableLiveData<ChannelObject>()
    val TAG: String = "DashboardActivityVM"
    private var compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var context: Application

    fun getClientDetail() {
        loading.value = true
        val loginDisposable = appDataManager.getClientDetail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<JsonObject>>() {
                override fun onComplete() {
                    loading.value = false
                }

                override fun onNext(value: Response<JsonObject>) {

                    if(value.code()==200) {

//            {"name":"Android","tagline":"SDK testing","teams":[{"id":93,"name":"Teating"}],"hide_launcher":false,"show_send_button":true,"show_close_button":true,"auto_focus":true}
                        if (value.body()?.toString()!!.startsWith("{")) {
                            widgetInfo.value = value.body()?.toString()
//                            error.value = value.body()?.get("msg").toString()
                        }
                    }
                    else {
                        error.value=value.message()
                    }

                }

                override fun onError(e: Throwable) {
                    error.value= e.message
                    loading.value = false
                }
            })
        compositeDisposable.add(loginDisposable)
    }

//    "channel": <channel-name-if-already present>, // other parameters are ignored if channel passed
//    "name": <name>,
//    "number": <number>,
//    "mail": <mail>,
//    "unique_id": <unique_id>,
//    "team_id": <team_id>
    fun getChannelDetail(channel: String?, name : String?, number : String?, mail:String?, unique_id : String, team_id : String?) {
        loading.value = true
        val loginDisposable = appDataManager.getChannelDetail(channel,name,number,mail,unique_id,team_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<JsonObject>>() {
                override fun onComplete() {
                    loading.value = false
                }

                override fun onNext(value: Response<JsonObject>) {
//                    {"status":"Success","message":"channel created","body":{"id":104163,"channel":"ch-user-32.4f49ec6157494a93a56923984a4241e4",
//                        "uuid":"06f32e87-2bb5-4a9e-99fb-09db27c7b555","last_read":null,"team_id":null,"name":"Lime Lobster","number":null,"mail":null,"unique_id":null,
//                        "assigned_to":null,"is_closed":false}}
                    if(value.code()==201 && value.body()?.get("status")?.asString.equals("Success")) {

//            {"name":"Android","tagline":"SDK testing","teams":[{"id":93,"name":"Teating"}],"hide_launcher":false,"show_send_button":true,"show_close_button":true,"auto_focus":true}
                        if (value.body()?.toString()!!.startsWith("{")) {
                            var channelObject = Gson().fromJson(value.body()?.get("body")?.asJsonObject, ChannelObject::class.java)

                           if(channelObject.team_id==null)
                               channelObject.team_id=team_id
                            channelInfo.value = channelObject
//                            error.value = value.body()?.get("msg").toString()
                        }
                    }
                    else {
                        error.value=value.message()
                    }

                }

                override fun onError(e: Throwable) {
                    error.value= e.message
                    loading.value = false
                }
            })
        compositeDisposable.add(loginDisposable)
    }


    fun getChannelDetailFromDB( team_id : String?) {
        var t=team_id
        if(t==null)
            t="default_team"
        loading.value = true
        val loginDisposable = appDataManager.getChannelByTeamID(t)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                loading.value=false
                var channelObject:ChannelObject=it
//                if(channelObject==null) {
//                    channelObject = ChannelObject(-1,null,null,null,team_id,null,null,null,null/*,false,null*/)
////                    channelObject.
//
//                }
                channelObjectData.value=channelObject
            },
                { errors ->
                    Log.e(TAG, "Unable to get username", errors)
                    loading.value=false
                     if(errors is EmptyResultSetException)
                         channelObjectData.value=ChannelObject(-1,null,null,null,t,null,null,null,null/*,false,null*/)
                    else
                         error.value=errors.message
                })
        compositeDisposable.add(loginDisposable)
    }
    fun insertOnDB(channelObject: ChannelObject) {
        loading.value=true
        val loginDisposable = appDataManager.insertChannel(channelObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                loading.value=false
//                var channelObject:ChannelObject=it
//                if(channelObject==null) {
//                    channelObject = ChannelObject(-1,null,null,null,team_id,null,null,null,null/*,false,null*/)
////                    channelObject.
//
//                }
//                channelObjectData.value=channelObject
            },
                { error -> Log.e(TAG, "Unable to get username", error)
                    loading.value=false
                })
        compositeDisposable.add(loginDisposable)
    }


    fun getPubnubDetail(teamId: String?) {
        loading.value = true
        val loginDisposable = appDataManager.getPubnubDetail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<JsonObject>>() {
                override fun onComplete() {
                    loading.value = false
                }

                override fun onNext(value: Response<JsonObject>) {
//                    {"pubkey":"pub-c-7efeae95-f505-4c40-99c4-04015a415abe",
//                        "subkey":"sub-c-41ea6378-7d3f-11e9-945c-2ea711aa6b65","authkey":"a6cfa71a1c774a5ab08e168fe17a0127"}
                    if(value.code()==200 && value.body()?.toString()!!.startsWith("{")) {
                        var a=ArrayList<String>()

                        a?.add(value.body()?.toString()!!)
                        a?.add(teamId!!)
                        pubnubInfo.value=a
                    }
                    else {
                        error.value=value.message()
                    }

                }

                override fun onError(e: Throwable) {
                    error.value= e.message
                    loading.value = false
                }
            })
        compositeDisposable.add(loginDisposable)
    }
}