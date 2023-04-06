package com.example.webapplication.domain

class AddItemsListUseCase(private val repository: Repository) {
    suspend fun addItemsList(list: List<WebItemEntity>) {
        repository.addItemsList(list)
    }
}