package com.example.webapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class WebAdapter : ListAdapter<WebItem, WebAdapter.MyViewHolder>(ShopItemDiffCallback()) {

    private lateinit var context: Context

    private lateinit var mClickListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_item, parent, false)
        return MyViewHolder(view, mClickListener)
    }


    override fun getItemCount(): Int {
        // change it
        return currentList.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            webPageName.text = getItem(position).siteName

            val imageUrl = getItem(position).imageUrl
//            Glide.with(context)
//                .load(imageUrl)
//                .placeholder(R.drawable.apple)
//                .into(holder.webPageLogo)
            holder.webPageLogo.setImageResource(getItem(position).imageUrl)

        }
    }

    inner class MyViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val webPageLogo: ImageView = itemView.findViewById(R.id.img_web_page_logo)
        val webPageName: TextView = itemView.findViewById(R.id.tv_web_page_name)

        init {

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}