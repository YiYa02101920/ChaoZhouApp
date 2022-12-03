package com.example.materialtest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CZAdapter(val context: Context, val fruitList: List<ChaoZhou>) : RecyclerView.Adapter<CZAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
//        val fruitText:TextView = view.findViewById(R.id.ContentText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.chaozhou_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val fruit = fruitList[position]
            val intent = Intent(context, CZActivity::class.java).apply {
                putExtra(CZActivity.FRUIT_NAME, fruit.name)
                putExtra(CZActivity.FRUIT_IMAGE_ID, fruit.imageId)
                putExtra(CZActivity.FRUIT_TEXT, fruit.textt)
            }
            context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitName.text = fruit.name

        Glide.with(context).load(fruit.imageId).into(holder.fruitImage)

    }

    override fun getItemCount() = fruitList.size

}