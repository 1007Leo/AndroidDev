package com.mmcs.memeapiclient.apimodel

import com.mmcs.memeapiclient.database.ImageModel

data class MemesGetResponseBody(
    val success: Boolean,
    val data: Memes
)

data class Memes(
    val memes: List<ImageModel>
)