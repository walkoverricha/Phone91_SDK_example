package com.phone91.sdk.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by richa WALK311 on 09/04/2020.
 */

//{"name":"Android","tagline":"SDK testing","teams":[{"id":93,"name":"Teating"}],"hide_launcher":false,"show_send_button":true,"show_close_button":true,"auto_focus":true}
data class WidgetObject(var name: String? = null,
                        var tagline: String? = null,
                        var hide_launcher: Boolean = false,
                        var show_send_button: Boolean = false,
                        var show_close_button: Boolean = false,
                        var auto_focus: Boolean = false,
                        var teams : ArrayList<TeamObject>?=null
                         ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(tagline)
        parcel.writeByte(if (hide_launcher) 1 else 0)
        parcel.writeByte(if (show_send_button) 1 else 0)
        parcel.writeByte(if (show_close_button) 1 else 0)
        parcel.writeByte(if (auto_focus) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WidgetObject> {
        override fun createFromParcel(parcel: Parcel): WidgetObject {
            return WidgetObject(parcel)
        }

        override fun newArray(size: Int): Array<WidgetObject?> {
            return arrayOfNulls(size)
        }
    }


}