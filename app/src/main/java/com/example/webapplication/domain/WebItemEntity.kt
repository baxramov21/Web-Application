package com.example.webapplication.domain


data class WebItemEntity(
    val siteName: String,
    val siteURL: String,
    val imageUrl: Int,
    var ID: Int = 0
)