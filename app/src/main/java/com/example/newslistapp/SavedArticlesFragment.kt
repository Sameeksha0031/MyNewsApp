package com.example.newslistapp

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newslistapp.adapters.RemoveOperation
import com.example.newslistapp.adapters.SavedNewsAdapter
import com.example.newslistapp.databinding.FragmentSavedArticlesBinding
import com.example.newslistapp.viewModels.NewsViewModel
import okhttp3.internal.notify
import javax.inject.Inject

class SavedArticlesFragment : Fragment(), RemoveOperation {

    @Inject
    lateinit var viewModel: NewsViewModel

    @Inject
    lateinit var viewmodelfactoryFactory: NewsViewModelFactory

    lateinit var binding: FragmentSavedArticlesBinding
    lateinit var savedNewsAdapter: SavedNewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentSavedArticlesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as NewsApplication).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this,viewmodelfactoryFactory).get(NewsViewModel::class.java)
        binding.recyclerViewOfSavedNews.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        viewModel.getArticlesFromDataBase()
        viewModel.savedNewsFlow.asLiveData().observe(viewLifecycleOwner){
            savedNewsAdapter = SavedNewsAdapter(requireContext(),it)
            savedNewsAdapter.removedArticled(this)
            binding.recyclerViewOfSavedNews.adapter = savedNewsAdapter
        }
    }

    override fun removeSavedNews(id: Int) {
        viewModel.removeArticleFromDataBase(id)
        viewModel.getArticlesFromDataBase()
    }
}