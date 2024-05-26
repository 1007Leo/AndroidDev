package com.mmcs.memeapiclient.viewmodel


import com.mmcs.memeapiclient.apimodel.MemesGetResponseBody
import com.mmcs.memeapiclient.apimodel.MemesPostResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface IMemeApiRetrofit {
    @GET("get_memes")
    fun listMemes(): Call<MemesGetResponseBody>
    @FormUrlEncoded
    @POST("caption_image")
    fun addCaption(@Field("template_id") template_id: String,
                   @Field("username") username: String = "LabApiAccount",
                   @Field("password") password: String = "j):;D6Mc4.VV]Hj",
                   @Field("boxes[][text]") boxes: List<String>
                   ): Call<MemesPostResponseBody>
}