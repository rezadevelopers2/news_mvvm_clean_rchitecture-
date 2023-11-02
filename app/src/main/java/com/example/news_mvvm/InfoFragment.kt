package com.example.news_mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.news_mvvm.databinding.FragmentInfoBinding
import com.example.news_mvvm.presentation.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar


class InfoFragment : Fragment() {


  private lateinit var binding : FragmentInfoBinding
  private lateinit var viewModel: NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        val args : InfoFragmentArgs by navArgs()
        val article = args.selectedArticle
        binding.webviewInfo.apply {
            webViewClient = WebViewClient()
            if (article.url != null && article.url != ""){
                loadUrl(article.url)
            }
        }

        binding.flBtn.setOnClickListener {

            viewModel.saveArticle(article)
            Snackbar.make(view,"Saved SuccessFully",Snackbar.LENGTH_LONG).show()
        }
    }
}