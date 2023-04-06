package com.example.webapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MainDao {

    @Query("SELECT * FROM universe_items_table")
    suspend fun getUniverseItems(): LiveData<List<WebItemDbModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: WebItemDbModel)

    @Insert
    suspend fun addListOfItems(items: List<WebItemDbModel>)

    @Query("DELETE FROM universe_items_table WHERE ID=:itemID")
    suspend fun deleteItem(itemID: Int)

    @Update
    suspend fun updateItem(item: WebItemDbModel)
}