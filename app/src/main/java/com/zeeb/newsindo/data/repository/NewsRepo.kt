package com.zeeb.newsindo.data.repository

import com.zeeb.newsindo.domain.Article
import io.reactivex.Single

interface NewsRepo {
    fun getNews(): Single<List<Article>>
}