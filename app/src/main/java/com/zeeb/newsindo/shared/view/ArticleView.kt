package com.zeeb.newsindo.shared.view

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.zeeb.newsindo.R
import com.zeeb.newsindo.domain.Article
import com.zeeb.newsindo.shared.extentions.loadSrc
import kotlinx.android.synthetic.main.item_news.view.*

class ArticleView(private val articlesItem: Article) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {

        val imgArticle = viewHolder.itemView.imgArticle
        val titleArticle = viewHolder.itemView.titleArticle
        val snippetArticle = viewHolder.itemView.snippetArticle
        val pubDateArticle = viewHolder.itemView.pubDateArticle

        imgArticle.loadSrc(articlesItem.imageUrl)
        titleArticle.text = articlesItem.title
        snippetArticle.text = articlesItem.author
        pubDateArticle.text = articlesItem.pubDate
    }

    override fun getLayout(): Int = R.layout.item_news



}