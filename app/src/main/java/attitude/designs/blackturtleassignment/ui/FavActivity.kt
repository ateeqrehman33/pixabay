package attitude.designs.blackturtleassignment.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import attitude.designs.blackturtleassignment.R
import attitude.designs.blackturtleassignment.adapters.PhotoAdapter
import attitude.designs.blackturtleassignment.models.Photo
import attitude.designs.blackturtleassignment.repo.DatabaseService
import java.util.*


lateinit var recyclerView: RecyclerView
var photos = ArrayList<Photo>()
var favAdapter: PhotoAdapter? = null

lateinit var database: DatabaseService

class FavActivity : AppCompatActivity() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)
        database = DatabaseService(this)
        setTitle("My fav")


        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)


        AsyncTask.execute {
            photos = database.getAllOperations() as ArrayList<Photo>
                // Image added successfully to your database
                Log.d("testing", photos?.size.toString())

            for(photo in photos!!){

                Log.d("testing", photo.previewURL)


            }

            if(photos!!.size>0){

                favAdapter = PhotoAdapter(
                    photos,
                    this)

                recyclerView.adapter = favAdapter


            }






        }



        if(photos!!.size<1 ) {

            Toast.makeText(
                this,
                "No fav added",
                Toast.LENGTH_LONG
            ).show()

        }





    }

    override fun onClick(view: View?) {
        val intent = Intent(this, DetailActivity::class.java)
        val holder = view?.tag as PhotoAdapter.PhotoViewHolder

        intent.putExtra(DetailActivity.PHOTO, favAdapter?.getPhoto(holder.adapterPosition))
        startActivity(intent)

    }
}