package com.mmcs.memeapiclient.viewmodel

import com.mmcs.memeapiclient.apimodel.MemesPostBody
import com.mmcs.memeapiclient.apimodel.MemesPostResponseBody

interface IMemeApiClient<T> {
    fun getAllImagesFromApi(callback: (List<T>) -> Unit)
    fun getRandomImageFromApi(callback: (T) -> Unit)
    fun postAddCaptions(body: MemesPostBody, callback: (MemesPostResponseBody) -> Unit)

    fun getAllImages(): List<T>
    fun getImage(ind: Int): T
    fun addImage(image: T)
    fun deleteImage(ind: Int)
    fun updateImage(image: T)
}