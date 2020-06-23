package com.phone91.sdk.data.preference

//import com.google.android.gms.maps.model.LatLng


interface PreferenceSource {

    fun setWidgetToken(widgetToken: String)
    fun getWidgetToken(): String?
    fun getChannel(): String?

    fun setChannel(channel: String)
    fun getUUID(): String?
    fun setUUID(UUID: String)
     fun getPubkey(): String?
     fun setPubkey(pubkey: String)
     fun getSubkey(): String?
     fun setSubkey(subkey: String)
     fun getAuth(): String?
     fun setAuth(auth: String)


}