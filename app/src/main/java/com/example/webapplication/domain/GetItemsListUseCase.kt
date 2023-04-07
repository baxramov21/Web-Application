package com.example.webapplication.domain

import androidx.lifecycle.LiveData

class GetItemsListUseCase(private val repository: Repository) {
    fun getItemsList(): LiveData<List<WebItemEntity>> {
        return repository.getItems()
    }
}