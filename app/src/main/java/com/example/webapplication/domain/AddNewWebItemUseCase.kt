package com.example.webapplication.domain

class AddNewWebItemUseCase(private val repository: Repository) {
    suspend fun addNewItem(item: WebItemEntity) {
        repository.addNewItem(item)
    }
}