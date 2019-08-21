package com.zeeb.newsindo.data.services

import com.zeeb.newsindo.data.response.TopHeadlineResponse
import io.reactivex.Single
import retrofit2.http.GET

interface NewsServices {

    @GET("top-headlines?country=id&apiKey=7659de32d86f41bfbf3864f433c6252b")
    fun getNews(): Single<TopHeadlineResponse>
}