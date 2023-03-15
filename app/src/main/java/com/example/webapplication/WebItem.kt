package com.example.webapplication

data class WebItem(
    val siteName: String,
    val siteURL: String,
    val imageUrl: Int,
    var ID: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}