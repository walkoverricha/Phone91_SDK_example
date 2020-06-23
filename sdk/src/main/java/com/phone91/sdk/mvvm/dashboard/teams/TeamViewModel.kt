package com.phone91.sdk.mvvm.dashboard.teams

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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



class TeamViewModel
@Inject
constructor(var appDataManager: AppDataManager) : ViewModel() {

    var error = MutableLiveData<String>()
    var latest_videos = MutableLiveData<ArrayList<String>>()
    var loading = MutableLiveData<Boolean>()
//    var sucesss = MutableLiveData<QouteObject>()
    var loggedout = MutableLiveData<Boolean>()

    private var compositeDisposable = CompositeDisposable()

//    fun getQuotes() {
//        loading.value = true
//        val loginDisposable = appDataManager.getQuotes()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(object : DisposableObserver<Response<JsonObject>>() {
//                override fun onComplete() {
//                    loading.value = false
//                }
//
//                override fun onNext(value: Response<JsonObject>) {
//
//                    if(value.code()==200){
//                        if(value.body()?.get("status")!!.toString().equals("1")) {
//                            var jsonObject= value.body()?.get("responseData")!!.asJsonObject
//
//                            if(jsonObject.get("app_access_monthly_status").asInt==1) {
////                                latest_videos.value=jsonObject.getAsJsonArray("latest_videos").
//
////                                jsonObject.getAsJsonArray("latest_videos").forEachIndexed { index, jsonElement ->
////                                    var url = Gson().fromJson(jsonElement, String::class.java)
////                                    var list= ArrayList<String>()
////                                    list.add(url)
////
////                                }
//
//                                /*val groupListType = object : TypeToken<ArrayList<String>>() {}*///ArrayList<String>()::class.java
//                                val list = Gson().fromJson( jsonObject.getAsJsonArray("latest_videos"), ArrayList<String>()::class.java)
//                                  latest_videos.value=list
//                                jsonObject.getAsJsonArray("data").forEachIndexed { index, jsonElement ->
////                                    var qouteObject = Gson().fromJson(jsonElement, QouteObject::class.java)
////                                    sucesss.value = qouteObject
//
//                                }
//                            }else if(value.body()?.get("msgCode").toString().equals("407")){
//                                loggedout.value = true
//                                error.value="Session has been expire. Please login again."
//                            }else{
////                                sucesss.value = null
//                            }
//
//                        }else if(value.body()?.get("msgCode").toString().equals("407")){
//                            loggedout.value = true
//                            error.value="Session has been expire. Please login again."
//                        }else{
//                            error.value= value.body()?.get("msgCode").toString()+" "+value.body()?.get("msg").toString()
//                        }
//                    }else {
//                        error.value=value.message()
//                    }
//
//                }
//
//                override fun onError(e: Throwable) {
//                    loading.value = false
//                }
//            })
//        compositeDisposable.add(loginDisposable)
//
//
//    }

}
