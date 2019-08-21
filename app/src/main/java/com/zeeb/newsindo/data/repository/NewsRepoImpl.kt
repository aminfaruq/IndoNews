package com.zeeb.newsindo.data.repository

import com.zeeb.newsindo.data.mapper.ArticleMapper
import com.zeeb.newsindo.data.services.NewsServices
import com.zeeb.newsindo.domain.Article
import io.reactivex.Single

open class NewsRepoImpl (
    private val newsServices : NewsServices,
    private val articleMapper: ArticleMapper

) : NewsRepo{
    override fun getNews(): Single<List<Article>> {
        return newsServices.getNews().map { articleMapper.mapToListDomain(it.response.docs) }
    }

}