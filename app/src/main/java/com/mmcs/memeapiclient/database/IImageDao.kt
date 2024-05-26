package com.mmcs.memeapiclient.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface IImageDao {
    @Insert
    fun insertImage(image: ImageModel)

    @Delete
    fun deleteImage(image: ImageModel)

    @Update
    fun updateImage(image: ImageModel)

    @Query("SELECT * FROM imagemodel WHERE id=:id")
    suspend fun getImage(id: Int): ImageModel
    @Query("SELECT * FROM imagemodel")
    fun getAllImages(): List<ImageModel>

    @Query("SELECT COUNT(id) FROM imagemodel")
    fun getImageCount(): Int
}