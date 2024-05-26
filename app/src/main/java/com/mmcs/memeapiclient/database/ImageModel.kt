package com.mmcs.memeapiclient.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ImageModel (
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var url: String = "",
    var og_url: String = "",
    var width: Int = 0,
    var height: Int = 0,
    var box_count: Int = 0,
    var captions: Int = 0

)