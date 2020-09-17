package attitude.designs.blackturtleassignment.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import attitude.designs.blackturtleassignment.R
import attitude.designs.blackturtleassignment.adapters.PhotoAdapter
import attitude.designs.blackturtleassignment.adapters.RvAdapter
import attitude.designs.blackturtleassignment.models.CategoryModel

class MainActivity : AppCompatActivity() , View.OnClickListener {

    var catAdapter: RvAdapter? = null


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        Initializing the type of layout, here I have used LinearLayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
//        Create an arraylist and adding category
        val dataList = ArrayList<CategoryModel>()
        dataList.add(CategoryModel("backgrounds", 1))
        dataList.add(CategoryModel("fashion", 2))
        dataList.add(CategoryModel("nature", 3))
        dataList.add(CategoryModel("science", 4))
        dataList.add(CategoryModel("education", 5))
        dataList.add(CategoryModel("feelings", 6))
        dataList.add(CategoryModel("health", 7))
        dataList.add(CategoryModel("people", 8))
        dataList.add(CategoryModel("religion", 9))
        dataList.add(CategoryModel("places", 10))
        dataList.add(CategoryModel("animals", 11))
        dataList.add(CategoryModel("industry", 12))
        dataList.add(CategoryModel("computer", 13))
        dataList.add(CategoryModel("food", 14))
        dataList.add(CategoryModel("sports", 15))
        dataList.add(CategoryModel("travel", 16))
        dataList.add(CategoryModel("transportation", 17))
        dataList.add(CategoryModel("buildings", 16))
        dataList.add(CategoryModel("business", 18))
        dataList.add(CategoryModel("music", 19))
//        pass the values to RvAdapter
        catAdapter = RvAdapter(dataList,this)
//        set the recyclerView to the adapter
        recyclerView.adapter = catAdapter;
    }


    override fun onClick(view: View?) {
        val intent = Intent(this, PhotoActivity::class.java)
        val holder = view?.tag as RvAdapter.ViewHolder

        intent.putExtra("catname", catAdapter?.getCat(holder.adapterPosition)?.name.toString())
        startActivity(intent)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.moreVertical -> {
            // do stuff
            val intent = Intent(this, FavActivity::class.java)
            startActivity(intent)

            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}