package com.phone91.sdk.data.preference

import android.content.Context
//import com.google.android.gms.maps.model.LatLng
import com.phone91.sdk.di.qualifiers.PreferenceInfo
import javax.inject.Inject

class AppPreferenceManager
@Inject
constructor(private val context: Context, @PreferenceInfo prefFileName: String) : Preferences(), PreferenceSource {


    override fun getWidgetToken(): String? {
        return this.mWidgetToken
    }


    override fun setWidgetToken(widgetToken: String) {
        this.mWidgetToken = widgetToken
    }

    override fun getChannel(): String? {
        return this.mChannel
    }

    override fun setChannel(channel: String) {
        this.mChannel = channel
    }


    override fun getUUID(): String? {
        return this.mUUID
    }

    override fun setUUID(UUID: String) {
        this.mUUID = UUID
    }

    override fun getPubkey(): String? {
        return this.mPubkey
    }

    override fun setPubkey(pubkey: String) {
        this.mPubkey = pubkey
    }

    override fun getSubkey(): String? {
        return this.mSubkey
    }

    override fun setSubkey(subkey: String) {
        this.mSubkey = subkey
    }


    override fun getAuth(): String? {
        return this.mAuth
    }

    override fun setAuth(auth: String) {
        this.mAuth = auth
    }


    init {
        Preferences.init(context, prefFileName)
    }

    var mWidgetToken by stringPref("widgetToken")
    var mChannel by stringPref("channel")
    var mUUID by stringPref("UUID")
    var mPubkey by stringPref("pubkey")
    var mSubkey by stringPref("subkey")
    var mAuth by stringPref("auth")


}