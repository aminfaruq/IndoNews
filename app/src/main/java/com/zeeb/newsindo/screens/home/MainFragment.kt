package com.zeeb.newsindo.screens.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.zeeb.newsindo.R
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import androidx.lifecycle.Observer
import com.zeeb.newsindo.shared.view.ArticleView
import com.zeeb.newsindo.shared.view.LoadmoreItemView
import com.zeeb.newsindo.shared.view.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject


class MainFragment : Fragment() {

    private val homeAdapter = GroupAdapter<ViewHolder>()
    private val vm: HomeVM by inject()

    private var isLoadMore = false
    private var loadmoreItemView = LoadmoreItemView()
    private var isLastPage = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayout = LinearLayoutManager(context)
        rvMain.apply {
            layoutManager = linearLayout
            adapter = homeAdapter

        }

        vm.homeState.observe(this, stateObser )
        vm.getNew()

        rvMain.addOnScrollListener(object : PaginationScrollListener(linearLayout){
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return this@MainFragment.isLoadMore
            }

            override fun loadMoreItems() {
                isLoadMore = true
                vm.getNew()
            }

        })

    }



    private val stateObser = Observer<HomeScreenState> {state ->
        when(state){
            is LoadingState ->{
                if (isLoadMore){
                    homeAdapter.add(loadmoreItemView)
                }else{

                }
            }
            is ErrorState -> {

            }
            is ArticleLoadedState -> {
                if (isLoadMore){
                    homeAdapter.remove(loadmoreItemView)
                    isLoadMore = false
                }
                state.articlesItem.map{
                    homeAdapter.add(ArticleView(it))
                }
            }
        }
    }
}
