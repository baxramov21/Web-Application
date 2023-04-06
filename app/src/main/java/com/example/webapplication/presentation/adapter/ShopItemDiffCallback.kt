package com.example.webapplication.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.webapplication.domain.WebItemEntity

class ShopItemDiffCallback : DiffUtil.ItemCallback<WebItemEntity>() {

    override fun areItemsTheSame(oldItem: WebItemEntity, newItem: WebItemEntity): Boolean {
        return oldItem.ID == newItem.ID
    }

    override fun areContentsTheSame(oldItem: WebItemEntity, newItem: WebItemEntity): Boolean {
        return oldItem == newItem
    }

}
