package com.mmcs.memeapiclient.apimodel

data class MemesPostResponseBody (
    var success: Boolean,
    var data: PostResponseData,
)

data class PostResponseData (
    var url: String,
    var page_url: String,
)