package com.example.news_mvvm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_mvvm.data.util.Resource
import com.example.news_mvvm.databinding.FragmentNewsBinding
import com.example.news_mvvm.presentation.adapter.NewsAdapter
import com.example.news_mvvm.presentation.viewModel.NewsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {
private lateinit var viewmodel :NewsViewModel
private lateinit var fragmentBinding:FragmentNewsBinding
private lateinit var newsAdapter: NewsAdapter
private val country = "us"
private var page = 1
private var isScrolling = false
private var isLoading = false
private var isLastPage = false
private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news,container,false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding = FragmentNewsBinding.bind(view)
        viewmodel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter

        newsAdapter.setOnItemClickListener {

            Log.i("TAG", "onViewCreated:$it ")
            val bundle = Bundle().apply {
                putSerializable("selected_article",it)
            }

            findNavController().navigate(R.id.action_newsFragment_to_infoFragment,
                bundle)

        }
        initRecycler()

        viewNewsList()
        setSearchView()
    }



    private fun viewNewsList() {
        viewmodel.getNewsHeadline(country, page)

        viewmodel.newsHeadlines.observe(viewLifecycleOwner) { response ->

            Log.i("TAG", "viewNewsList: $response")
            when (response) {
                is Resource.Success -> {

                    hiddenProgress()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                        if(it.totalResults%20 == 0){
                             pages = it.totalResults / 20

                        }else{
                            pages = it.totalResults / 20+1
                        }

                        isLastPage = page == pages
                    }
                }

                is Resource.Error -> {

                    hiddenProgress()
                    response.message?.let {
                        Toast.makeText(activity, " An error$it", Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {

                    shoProgress()
                }
            }
        }
    }


    private fun initRecycler(){
        //newsAdapter = NewsAdapter()

        fragmentBinding.rvNews.apply {
            fragmentBinding.rvNews.layoutManager = LinearLayoutManager(activity)
            fragmentBinding.rvNews.adapter = newsAdapter
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }
    }

    private fun setSearchView(){
        fragmentBinding.srNews.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewmodel.getSearchNewsHeadline(country,page,p0.toString())
                viewSearchNews()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if(!p0.isNullOrEmpty()){
                    MainScope().launch {
                        delay(2000)
                        viewmodel.getSearchNewsHeadline(country,page,p0.toString())
                        viewSearchNews()
                    }

                }

                return false
            }

        })

        fragmentBinding.srNews.setOnCloseListener(object :SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                initRecycler()
                viewNewsList()
                return false
            }

        })
    }



    private fun viewSearchNews(){
        viewmodel.searchNews.observe(viewLifecycleOwner) { response ->

            Log.i("TAG", "viewNewsList: $response")
            when (response) {
                is Resource.Success -> {

                    hiddenProgress()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                        if (it.totalResults % 20 == 0) {
                            pages = it.totalResults / 20

                        } else {
                            pages = it.totalResults / 20 + 1
                        }

                        isLastPage = page == pages
                    }
                }

                is Resource.Error -> {

                    hiddenProgress()
                    response.message?.let {
                        Toast.makeText(activity, " An error$it", Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {

                    shoProgress()
                }
            }
        }
    }




    private fun shoProgress(){
        isLoading = true
        fragmentBinding.prNews.visibility = View.VISIBLE
    }

    private fun hiddenProgress(){
        isLoading = false
        fragmentBinding.prNews.visibility = View.GONE
    }

    private  val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = fragmentBinding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItem = layoutManager.childCount
            val topPosition = layoutManager.findLastVisibleItemPosition()

            val hasReachedToEnd = topPosition+visibleItem >=sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if(shouldPaginate){
                page++
                viewmodel.getNewsHeadline(country,page)
                isScrolling = false
            }
        }
    }
}