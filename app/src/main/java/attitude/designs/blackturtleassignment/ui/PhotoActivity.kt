package attitude.designs.blackturtleassignment.ui

import android.content.Intent
import android.os.Bundle

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import attitude.designs.blackturtleassignment.R
import attitude.designs.blackturtleassignment.adapters.PhotoAdapter
import attitude.designs.blackturtleassignment.models.Photo
import attitude.designs.blackturtleassignment.models.PhotoList
import attitude.designs.blackturtleassignment.api.PhotoRetriever
import attitude.designs.blackturtleassignment.repo.MyDatabase

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class PhotoActivity : AppCompatActivity(), View.OnClickListener {



    lateinit var recyclerView: RecyclerView
    var photos = ArrayList<Photo>()
    var photoAdapter: PhotoAdapter? = null
    var cat: String? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_activity_main)



        val permissions = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this, permissions,0)

        cat = (intent.getSerializableExtra("catname") as String?)
        setTitle(cat)


        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this,2,RecyclerView.VERTICAL,false)

        var retriever = PhotoRetriever(cat)


        val callBack = object: Callback<PhotoList> {
            override fun onFailure(call: Call<PhotoList>?, t: Throwable?) {
                Log.e("MainActivity", "Error: " + t?.message)





            }

            override fun onResponse(call: Call<PhotoList>?, response: Response<PhotoList>?) {
                response?.isSuccessful.let {
                    this@PhotoActivity.photos = response?.body()?.hits as ArrayList<Photo>
                    photoAdapter = PhotoAdapter(this@PhotoActivity.photos!!,
                            this@PhotoActivity)

                    recyclerView.adapter = photoAdapter

                }

            }

        }

        retriever.getPhotos(callBack)







    }

    override fun onClick(view: View?) {
        val intent = Intent(this, DetailActivity::class.java)
        val holder = view?.tag as PhotoAdapter.PhotoViewHolder

        intent.putExtra(DetailActivity.PHOTO, photoAdapter?.getPhoto(holder.adapterPosition))
        startActivity(intent)

    }




}

