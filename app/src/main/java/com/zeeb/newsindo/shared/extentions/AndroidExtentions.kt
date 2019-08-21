package com.zeeb.newsindo.shared.extentions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadSrc(url: String){
    if (url.isEmpty())
        return
    Picasso.get().load(url).into(this)
}