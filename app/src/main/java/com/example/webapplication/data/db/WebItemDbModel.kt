package com.example.webapplication.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.webapplication.data.db.converters.DataConverter

@Entity(tableName = "universe_items_table")
@TypeConverters(value = [DataConverter::class])
data class WebItemDbModel(
    val siteName: String,
    val siteURL: String,
    val imageUrl: Int,
    @PrimaryKey(autoGenerate = true)
    var ID: Int
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}