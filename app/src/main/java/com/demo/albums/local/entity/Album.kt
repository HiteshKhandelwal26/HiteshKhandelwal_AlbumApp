package com.demo.albums.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/*Entity class, to create the Table name and the columns to save Album information*/
@Entity(tableName = "albums")
class Album() : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var tittle: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        tittle = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(tittle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Album> {
        override fun createFromParcel(parcel: Parcel): Album {
            return Album(parcel)
        }

        override fun newArray(size: Int): Array<Album?> {
            return arrayOfNulls(size)
        }
    }
}
