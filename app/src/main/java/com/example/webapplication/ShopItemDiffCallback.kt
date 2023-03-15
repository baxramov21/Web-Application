package com.example.webapplication

import androidx.recyclerview.widget.DiffUtil

class ShopItemDiffCallback : DiffUtil.ItemCallback<WebItem>() {

    override fun areItemsTheSame(oldItem: WebItem, newItem: WebItem): Boolean {
        return oldItem.ID == newItem.ID
    }

    override fun areContentsTheSame(oldItem: WebItem, newItem: WebItem): Boolean {
        return oldItem == newItem
    }

}
