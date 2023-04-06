package com.example.webapplication.domain

class EditWebItemUseCase(private val repository: Repository) {
    suspend fun changeItem(item: WebItemEntity) {
        repository.changeItem(item)
    }
}