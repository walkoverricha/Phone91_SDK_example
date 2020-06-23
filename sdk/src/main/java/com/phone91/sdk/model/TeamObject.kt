package com.phone91.sdk.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by richa WALK311 on 09/04/2020.
 */

//"teams":[{"id":93,"name":"Teating"}]
data class TeamObject(var id: String? = null,
                      var name: String? = null
                         ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TeamObject> {
        override fun createFromParcel(parcel: Parcel): TeamObject {
            return TeamObject(parcel)
        }

        override fun newArray(size: Int): Array<TeamObject?> {
            return arrayOfNulls(size)
        }
    }

}