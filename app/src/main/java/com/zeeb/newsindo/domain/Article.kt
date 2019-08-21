package com.zeeb.newsindo.domain

data class Article(
    val title: String,
    val snippet: String,
    val pubDate: String,
    val author: String,
    val url: String,
    val content: String,
    val imageUrl: String
)