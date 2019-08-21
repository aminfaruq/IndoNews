package com.zeeb.newsindo.shared.view

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.zeeb.newsindo.R

class LoadmoreItemView : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
    }

    override fun getLayout(): Int = R.layout.item_news
}