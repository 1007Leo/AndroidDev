package com.mmcs.memeapiclient.apimodel

data class MemesPostBody(
    val template_id: String,
    val username: String = "LabApiAccount",
    val password: String = "j):;D6Mc4.VV]Hj",
    val boxes: List<String>
)
