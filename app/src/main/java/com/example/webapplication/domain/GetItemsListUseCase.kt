package com.example.webapplication.domain

import androidx.lifecycle.LiveData

class GetItemsListUseCase(private val repository: Repository) {
    suspend fun getItemsList(): LiveData<List<WebItemEntity>> {
        return repository.getItems()
    }
}