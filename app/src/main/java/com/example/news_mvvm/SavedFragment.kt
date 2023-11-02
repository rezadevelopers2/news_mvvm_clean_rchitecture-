package com.example.news_mvvm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_mvvm.databinding.FragmentSavedBinding
import com.example.news_mvvm.presentation.adapter.NewsAdapter
import com.example.news_mvvm.presentation.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match

class SavedFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding : FragmentSavedBinding
    private lateinit var savedAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        savedAdapter = (activity as MainActivity).newsAdapter
        Log.i("TAG", "onCreate: ")

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedBinding.bind(view)
        initRecycler()

        Log.i("TAG", "savedInstanceState:$savedInstanceState ")
       // if(savedInstanceState == null){
            viewModel.getSavedNews().observe(viewLifecycleOwner) {
                Log.i("TAG", "onViewCreated:$it ")
                savedAdapter.differ.submitList(it)
            }
       // }

        savedAdapter.setOnItemClickListener {

            Log.i("TAG", "onViewCreated:$it ")
            val bundle = Bundle().apply {
                putSerializable("selected_article",it)
            }

            findNavController().navigate(R.id.action_savedFragment_to_infoFragment,
                bundle)

        }


        val itemTouchHelperCallBack = object :ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return true

            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               val position = viewHolder.adapterPosition
                val article = savedAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)
                Snackbar.make(view,"Delete SuccessFully,",Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo"){
                            viewModel.saveArticle(article)
                        }
                        show()
                    }
            }

        }

        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(binding.rvLocal)
        }
    }

    private fun  initRecycler(){
        binding.rvLocal.apply {
            adapter = savedAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i("TAG", "onPause: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i("TAG", "onResume: ")
    }

}