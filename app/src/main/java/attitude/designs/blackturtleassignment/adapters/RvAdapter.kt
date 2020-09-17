package attitude.designs.blackturtleassignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import attitude.designs.blackturtleassignment.R
import attitude.designs.blackturtleassignment.models.CategoryModel

class RvAdapter(val userList: ArrayList<CategoryModel>, var clickListener: View.OnClickListener?) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {


        val v = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_item_layout, p0, false)
        return ViewHolder(v);
    }
    override fun getItemCount(): Int {
        return userList.size
    }
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.name?.text = userList[p1].name
    }

    fun getCat(adapterPosition: Int): CategoryModel {
        return userList[adapterPosition]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        init {
            if (clickListener != null) {
                itemView.setOnClickListener(clickListener)
            }
            itemView.tag = this

        }


        val name = itemView.findViewById<TextView>(R.id.tvCount)

    }
}