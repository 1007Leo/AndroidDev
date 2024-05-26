package com.mmcs.memeapiclient.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ImageModel::class],
    version = 4
)
abstract class ImageDatabase: RoomDatabase() {
    abstract val dao: IImageDao
}