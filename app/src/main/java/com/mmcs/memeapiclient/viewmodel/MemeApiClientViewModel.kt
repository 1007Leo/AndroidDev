package com.mmcs.memeapiclient.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mmcs.memeapiclient.apimodel.MemesGetResponseBody
import com.mmcs.memeapiclient.apimodel.MemesPostBody
import com.mmcs.memeapiclient.apimodel.MemesPostResponseBody
import com.mmcs.memeapiclient.database.IImageDao
import com.mmcs.memeapiclient.database.ImageModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random


class MemeApiClientViewModel(private val dao: IImageDao): IMemeApiClient<ImageModel>, ViewModel() {
    var adapterPosition = -1

    var api = Retrofit.Builder()
        .baseUrl("https://api.imgflip.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IMemeApiRetrofit::class.java)

    override fun getAllImagesFromApi(callback: (List<ImageModel>) -> Unit) =
        api.listMemes().enqueue(object : Callback<MemesGetResponseBody>{
            override fun onResponse(call: Call<MemesGetResponseBody>, response: Response<MemesGetResponseBody>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback(it.data.memes) }
                }
            }

            override fun onFailure(call: Call<MemesGetResponseBody>, t: Throwable) {
                t.message?.let { Log.i("FAILURE_RESPONSE", it) }
            }

        })

    override fun getRandomImageFromApi(callback: (ImageModel) -> Unit) =
        api.listMemes().enqueue(object : Callback<MemesGetResponseBody>{
            override fun onResponse(call: Call<MemesGetResponseBody>, response: Response<MemesGetResponseBody>) {
                if (response.isSuccessful) {
                    val dataModel = response.body() as MemesGetResponseBody
                    val images = dataModel.data.memes
                    val ind = Random.nextInt(0, images.size)
                    val imageData = images.elementAt(ind)
                    callback(imageData)
                }
            }

            override fun onFailure(call: Call<MemesGetResponseBody>, t: Throwable) {
                t.message?.let { Log.i("FAILURE_RESPONSE", it) }
            }
        })

    override fun postAddCaptions(body: MemesPostBody, callback: (MemesPostResponseBody) -> Unit) =
        api.addCaption(
            body.template_id,
            body.username,
            body.password,
            body.boxes)
            .enqueue(object : Callback<MemesPostResponseBody> {
            override fun onResponse(
                call: Call<MemesPostResponseBody>,
                response: Response<MemesPostResponseBody>
            ) {
                if (response.isSuccessful) {
                    callback(response.body() as MemesPostResponseBody)
                }
            }

            override fun onFailure(call: Call<MemesPostResponseBody>, t: Throwable) {
                t.message?.let { Log.i("FAILURE_RESPONSE", it) }
            }

        })


    override fun getAllImages(): List<ImageModel> {
        return dao.getAllImages()
    }

    override fun getImage(ind: Int): ImageModel {
        return dao.getAllImages().elementAt(ind)
    }

    override fun deleteImage(ind: Int) {
        dao.deleteImage(getImage(ind))
    }

    override fun updateImage(image: ImageModel) {
        dao.updateImage(image)
    }

    override fun addImage(image: ImageModel) {
        dao.insertImage(image)
    }

    fun getImageCount(): Int {
        return dao.getImageCount()
    }
}