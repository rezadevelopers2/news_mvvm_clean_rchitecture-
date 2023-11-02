package com.example.news_mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.news_mvvm.databinding.ActivityMainBinding
import com.example.news_mvvm.presentation.adapter.NewsAdapter
import com.example.news_mvvm.presentation.viewModel.NewViewModelFactory
import com.example.news_mvvm.presentation.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: NewViewModelFactory
    @Inject
    lateinit var newsAdapter: NewsAdapter
    lateinit var viewModel :NewsViewModel
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,factory)[NewsViewModel::class.java]

       val navHostFragment = supportFragmentManager
           .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController)
    }
}