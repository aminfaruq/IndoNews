package com.zeeb.newsindo.screens.home

import androidx.lifecycle.MutableLiveData
import com.zeeb.newsindo.data.repository.NewsRepo
import com.zeeb.newsindo.domain.Article
import com.zeeb.newsindo.screens.base.BaseViewModel
import com.zeeb.newsindo.shared.RxUtils

sealed class HomeScreenState
object LoadingState : HomeScreenState()
data class ErrorState(var msg:String?) : HomeScreenState()
data class ArticleLoadedState(val articlesItem: List<Article>): HomeScreenState()

class HomeVM (val repo : NewsRepo) : BaseViewModel(){


    var homeState = MutableLiveData<HomeScreenState>()

    fun getNew(){
        homeState.value = LoadingState
        compositeDisposable.add(
            repo.getNews()
                .compose(RxUtils.applySingleAsync())
                .subscribe( {result ->
                    homeState.value = ArticleLoadedState(result)

                }, this::onError))
    }

    override fun onError(error: Throwable) {
        homeState.value = ErrorState(error.localizedMessage)

    }
}