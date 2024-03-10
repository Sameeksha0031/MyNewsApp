package com.example.newslistapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newslistapp.adapters.CrudOperationOnDataBaseListener
import com.example.newslistapp.adapters.NewListAdapter
import com.example.newslistapp.databinding.FragmentNewsListBinding
import com.example.newslistapp.model.Article
import com.example.newslistapp.utils.Constants
import com.example.newslistapp.viewModels.NewsViewModel
import javax.inject.Inject

class NewsListFragment : Fragment(),CrudOperationOnDataBaseListener {
    lateinit var binding: FragmentNewsListBinding
    lateinit var newsAdapter: NewListAdapter

    @Inject
    lateinit var newsViewModel: NewsViewModel

    @Inject
    lateinit var newsViewModelFactory: NewsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as NewsApplication).applicationComponent.inject(this)
        newsViewModel = ViewModelProvider(this,newsViewModelFactory).get(NewsViewModel::class.java)

        val listForSpinner = arrayListOf("India","US")
        binding.actionBarSpinner.adapter = ArrayAdapter(requireContext(),R.layout.spinner_layout,listForSpinner)
       // newsViewModel.getNews("us",getString(R.string.api_key))
        binding.recyclerViewOfNews.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)

        binding.button.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.viewContainerForFragment,SavedArticlesFragment())
                .addToBackStack(null)
                .commit()
        }

        setSpinnerValue()
        initObserver()
    }

    fun initObserver() {
//        newsViewModel.newLiveData.observe(viewLifecycleOwner) {
//            Log.d("#sam","getAllResponse = $it")
//            newsAdapter = NewListAdapter(requireContext(), it.articles)
//            binding.recyclerViewOfNews.adapter = newsAdapter
//        }

        newsViewModel.getNewsFlow.asLiveData().observe(viewLifecycleOwner) {
            Log.d("#sam","getfilterList = $it")
            newsAdapter = NewListAdapter(requireContext(), it.articles)
            binding.recyclerViewOfNews.adapter = newsAdapter
            newsAdapter.crudOperation(this)
        }
    }

    fun popFragment() {
        NewsListFragment().popFragment()
    }

    fun setSpinnerValue() {
        binding.actionBarSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var country = ""
                when(position) {
                    0 -> {country = "in"}
                    1 -> {country = "us"}
                }
                Log.d("#sam","country = $country")
                newsViewModel.getFilteredNewsList(country,Constants.API_KEY)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
              //  newsViewModel.getFilteredNewsList("us",Constants.API_KEY)
            }

        }
    }

    override fun addArticle(article: Article) {
        newsViewModel.addArticleToDataBase(article)
    }

    override fun removeArticle(id: Int) {
        TODO("Not yet implemented")
    }
}