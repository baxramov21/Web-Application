package com.example.webapplication.data.db.converters

import com.example.webapplication.data.db.WebItemDbModel
import com.example.webapplication.domain.WebItemEntity

class Mapper {

    fun dbModelListToEntityList(list: List<WebItemDbModel>): List<WebItemEntity> =
        list.map { dbModelToEntity(it) }

    fun entityListToDbModelList(list: List<WebItemEntity>): List<WebItemDbModel> =
        list.map { entityToDbModel(it) }

    fun dbModelToEntity(item: WebItemDbModel): WebItemEntity {
        return WebItemEntity(
            item.siteName,
            item.siteURL,
            item.imageUrl,
            item.ID,
        )
    }

    fun entityToDbModel(item: WebItemEntity): WebItemDbModel {
        return WebItemDbModel(
            item.siteName,
            item.siteURL,
            item.imageUrl,
            item.ID,
        )
    }
}