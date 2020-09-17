package attitude.designs.blackturtleassignment.api

import attitude.designs.blackturtleassignment.models.PhotoList
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PhotoRetriever(cat:String?) {

    private val service: PhotoAPI
    private var cat:String = cat as String

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(PhotoAPI::class.java)
    }

    fun getPhotos(callBack: Callback<PhotoList>) {
        val call = service.getPhotos(cat)
        call.enqueue(callBack)
    }

    fun getPhoto(callBack: Callback<PhotoList>) {
        val call = service.getPhoto(cat)
        call.enqueue(callBack)
    }

}