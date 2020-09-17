package attitude.designs.blackturtleassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import attitude.designs.blackturtleassignment.R
import attitude.designs.blackturtleassignment.models.Photo
import attitude.designs.blackturtleassignment.models.PhotoList
import attitude.designs.blackturtleassignment.api.PhotoRetriever
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    val SPLASH_TIME: Long = 3000
    var photos: List<Photo>? = null
    var photo: Photo?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        var retriever = PhotoRetriever("dark")


        val callBack = object: Callback<PhotoList> {
            override fun onFailure(call: Call<PhotoList>?, t: Throwable?) {
                Log.e("MainActivity", "Error: " + t?.message)
            }

            override fun onResponse(call: Call<PhotoList>?, response: Response<PhotoList>?) {
                response?.isSuccessful.let {
                    this@SplashActivity.photos = response?.body()?.hits


                    val randomNumber = (0..9).random()

                    photo = photos?.get(randomNumber)

                    val iv= findViewById(R.id.iv) as ImageView

                    if (photo?.previewURL!!.isNotEmpty()) {
                        Glide.with(applicationContext)
                            .load(photo?.previewURL)
                            .into(iv)
                    }


                }
            }

        }

        retriever.getPhoto(callBack)







        val handler = Handler();
        handler.postDelayed({

            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }, SPLASH_TIME)


    }
}