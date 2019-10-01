package com.zeeb.newsindo.di

import com.google.gson.GsonBuilder
import com.zeeb.newsindo.data.mapper.ArticleMapper
import com.zeeb.newsindo.data.repository.NewsRepo
import com.zeeb.newsindo.data.repository.NewsRepoImpl
import com.zeeb.newsindo.data.services.NewsInterceptor
import com.zeeb.newsindo.data.services.NewsServices
import com.zeeb.newsindo.screens.home.HomeVM
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { NewsInterceptor() }
    single { createOkHttpClient(get()) }
    single { createWebService<NewsServices>(get(),"https://newsapi.org/v2/") }
}

//gw bau


val dataModule = module {

    //repo
    single { NewsRepoImpl(get(), get()) as NewsRepo }
    //
    single { ArticleMapper() }
    //
    viewModel { HomeVM(get()) }

}

fun createOkHttpClient(interceptor: NewsInterceptor) : OkHttpClient{
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val timeout = 60L
    return OkHttpClient.Builder()
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(interceptor)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T{
    val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        return retrofit.create(T::class.java)
}

val myAppModule = listOf(appModule, dataModule)
