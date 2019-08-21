package com.zeeb.newsindo.data.mapper

import com.zeeb.newsindo.data.model.*
import com.zeeb.newsindo.domain.Article

open class ArticleMapper : BaseMapper<ArticleModel, Article> {


    override fun mapToDomain(model: ArticleModel): Article {
        val url = if(model.multimedia!!.isEmpty()) {
                    model.web_url
        }else{
            "https://eumeps.org/assets/generated/images/news-placeholder.png"
        }
        return Article(
            title = model.headline.main,
            snippet = model.snippet,
            pubDate = model.pub_date,
            author = model.byline.original,
            content = model.lead_paragraph ?: "",
            url = model.web_url,
            imageUrl = url

        )
    }

    override fun mapToModel(domain: Article): ArticleModel {
        val headline = HeadlineModel(domain.title)
        val byLine = ByLineModel(domain.author)
        val multimedia = MultimediaModel(domain.url)
        val lismultimedia = ArrayList<MultimediaModel>()
        lismultimedia.add(multimedia)
        return ArticleModel(
            headline = headline,
            snippet = domain.snippet,
            pub_date = domain.pubDate,
            byline = byLine,
            web_url = domain.url,
            lead_paragraph = domain.content,
            multimedia = lismultimedia

        )
    }

}