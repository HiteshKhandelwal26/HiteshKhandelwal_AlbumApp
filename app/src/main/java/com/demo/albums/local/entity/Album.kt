package com.demo.albums.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/*Entity class, to create the Table name and the columns to save Album information*/
@Entity(tableName = "albums")
class Album : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var tittle: String? = null
}
