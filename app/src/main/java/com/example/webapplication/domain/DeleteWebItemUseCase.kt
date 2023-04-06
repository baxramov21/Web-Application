package com.example.webapplication.domain

class DeleteWebItemUseCase(private val repository: Repository) {
    suspend fun deleteItem(item: WebItemEntity) {
        repository.deleteItem(item)
    }
}