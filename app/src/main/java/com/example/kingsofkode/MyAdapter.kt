package com.example.kingsofkode

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.kingsofkode.MyAdapter.MyViewHolder
import java.util.*

class MyAdapter(private val context: Context, private val arrayList: ArrayList<String>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.character_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val image_id = context.resources.getIdentifier(
            arrayList[position % arrayList.size],
            "drawable",
            context.packageName
        )
        holder.character_imageview.setImageResource(image_id)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var character_imageview: ImageView

        init {
            character_imageview = itemView.findViewById(R.id.character_imageview)
        }
    }
}