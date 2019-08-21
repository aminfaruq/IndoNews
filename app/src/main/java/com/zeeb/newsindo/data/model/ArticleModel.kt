package com.zeeb.newsindo.data.model

data class ArticleModel(
    val headline: HeadlineModel,
    val snippet: String,
    val pub_date: String,
    val byline: ByLineModel,
    val web_url: String,
    val lead_paragraph: String?,
    val multimedia: List<MultimediaModel>
)