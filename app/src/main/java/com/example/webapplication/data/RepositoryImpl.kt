package com.example.webapplication.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.webapplication.data.db.MainDatabase
import com.example.webapplication.data.db.converters.Mapper
import com.example.webapplication.domain.Repository
import com.example.webapplication.domain.WebItemEntity

class RepositoryImpl(application: Application) : Repository {

    private val database = MainDatabase.getInstance(application).getDao()
    private val mapper = Mapper()


    override fun getItems(): LiveData<List<WebItemEntity>> =
        MediatorLiveData<List<WebItemEntity>>().apply {
            addSource(database.getUniverseItems()) {
                value = mapper.dbModelListToEntityList(it)
            }
        }

    override suspend fun deleteItem(item: WebItemEntity) {
        database.deleteItem(
            mapper.entityToDbModel(item).ID
        )
    }

    override suspend fun addItemsList(itemsList: List<WebItemEntity>) {
        database.addListOfItems(
            mapper.entityListToDbModelList(itemsList)
        )
    }

    override suspend fun addNewItem(item: WebItemEntity) {
        database.addItem(
            mapper.entityToDbModel(item)
        )
    }

    override suspend fun changeItem(item: WebItemEntity) {
        database.updateItem(
            mapper.entityToDbModel(item)
        )
    }
}