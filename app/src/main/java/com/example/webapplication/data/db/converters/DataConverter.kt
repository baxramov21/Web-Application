package com.example.webapplication.data.db.converters

import androidx.room.TypeConverter
import com.example.webapplication.domain.WebItemEntity
import com.google.gson.Gson
import java.io.Serializable


class DataConverter : Serializable {

    @TypeConverter
    fun fromWebItemsList(listOfItems: List<WebItemEntity>?): String {
        return Gson().toJson(listOfItems)
    }

    @TypeConverter
    fun toWebItemsList(optionValuesString: String): List<WebItemEntity> {
        val gson = Gson()
        val objects = gson.fromJson(optionValuesString, ArrayList::class.java)
        val webItems = ArrayList<WebItemEntity>()
        for (o in objects) {
            webItems.add(gson.fromJson(o.toString(), WebItemEntity::class.java))
        }
        return webItems
    }
}