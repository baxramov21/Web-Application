package com.example.webapplication.domain

import androidx.lifecycle.LiveData

interface Repository {
    suspend fun addNewItem(item: WebItemEntity)
    suspend fun changeItem(item: WebItemEntity)
    suspend fun getItems(): LiveData<List<WebItemEntity>>
    suspend fun deleteItem(item: WebItemEntity)
    suspend fun addItemsList(itemsList: List<WebItemEntity>)
}