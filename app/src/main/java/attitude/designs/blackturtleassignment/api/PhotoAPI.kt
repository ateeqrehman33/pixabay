package attitude.designs.blackturtleassignment.api

import attitude.designs.blackturtleassignment.models.PhotoList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PhotoAPI {


    @GET("?key=18319575-1128c876e82470617451404a3&image_type=photo&orientation=vertical&per_page=100")
    fun getPhotos( @Query("category") aParam: String?): Call<PhotoList>

    @GET("?key=18319575-1128c876e82470617451404a3&image_type=photo&orientation=vertical&per_page=10")
    fun getPhoto( @Query("q") aParam: String?): Call<PhotoList>

}