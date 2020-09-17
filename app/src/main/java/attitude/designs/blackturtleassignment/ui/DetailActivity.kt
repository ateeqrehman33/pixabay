package attitude.designs.blackturtleassignment.ui

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import attitude.designs.blackturtleassignment.R
import attitude.designs.blackturtleassignment.models.Photo
import attitude.designs.blackturtleassignment.repo.DatabaseService
import com.bumptech.glide.Glide
import java.io.IOException
import java.io.InputStream
import java.net.URL


class DetailActivity : AppCompatActivity() {

    var button4: Button? = null
    var button5: Button? = null
    var favbtn: ImageView ?= null
    var photo: Photo?=null
    lateinit var database: DatabaseService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setTitle("Photo App Detail")

        database = DatabaseService(this)


                button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)

        favbtn = findViewById(R.id.favbtn)


        val ivPhoto = findViewById(R.id.ivPhoto) as ImageView
         photo = intent.getSerializableExtra(PHOTO) as Photo?

        photo?.webformatURL.let {
            Glide.with(this)
                    .load(photo?.webformatURL)
                    .into(ivPhoto)
        }


        favbtn?.setOnClickListener {


            Toast.makeText(
                this,
                "Added to fav",
                Toast.LENGTH_LONG
            ).show()

            AsyncTask.execute {
                val success = database.insert(photo!!)
                // Image added successfully to your database
                Log.d("testing","added : "+success)


            }
        }


        ivPhoto.setOnClickListener {
            finish()
        }

        button4?.setOnClickListener {


            val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val uri: Uri = Uri.parse(photo?.webformatURL)
            val request = DownloadManager.Request(uri)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            val reference = downloadManager.enqueue(request)




        }


        button5?.setOnClickListener {


            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val wpm = WallpaperManager.getInstance(this)
            var ins: InputStream? = null
            try {
                ins =
                    URL(photo?.webformatURL).openStream()
                wpm.setStream(ins)

                Toast.makeText(
                    this,
                    "Wallpaper Applied",
                    Toast.LENGTH_LONG
                ).show()


            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(
                    this,
                    "Wallpaper failed to apply",
                    Toast.LENGTH_LONG
                ).show()
            }


        }
    }

    companion object {
        val PHOTO = "PHOTO"
    }
}
