package com.example.newslistapp.adapters

import android.content.Context
import android.nfc.cardemulation.CardEmulation
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newslistapp.NewsListFragment
import com.example.newslistapp.R
import com.example.newslistapp.ReadFragment
import com.example.newslistapp.model.Article

class NewListAdapter(private val context : Context,val news : List<Article>) : RecyclerView.Adapter<NewListAdapter.NewListAdapterViewHolder>() {

    var crudOperationOnDataBaseListener : CrudOperationOnDataBaseListener ?= null
    class NewListAdapterViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var heading: TextView = item.findViewById(R.id.newTitle)
        var subTitle: TextView = item.findViewById(R.id.newsDescription)
        var outerCardLayout : CardView = item.findViewById(R.id.outerCardLayout)
    }

    fun crudOperation(crudOperationOnDataBase : CrudOperationOnDataBaseListener) {
        this.crudOperationOnDataBaseListener = crudOperationOnDataBase
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewListAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(context).inflate(R.layout.cardlayout_for_list, parent, false)
        return NewListAdapterViewHolder(layoutInflater)
    }


    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: NewListAdapterViewHolder, position: Int) {
        val newsList = news[position]
        holder.heading.text = newsList.title
        holder.subTitle.text = newsList.description

        holder.outerCardLayout.setOnClickListener {
            val fragment = ReadFragment()
            var bundle = Bundle()
            bundle.putString("selectedCard",newsList.url)
            fragment.arguments = bundle
            Log.d("#sam","adapter newsUrl = ${newsList.url}")

            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.viewContainerForFragment,fragment)
                .addToBackStack(null)
                .commit()
        }

        holder.outerCardLayout.setOnLongClickListener(object : OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                Log.d("#sam","OnLongPressed = $newsList")
                crudOperationOnDataBaseListener?.addArticle(newsList)
                return true
            }
        })
    }
}

interface CrudOperationOnDataBaseListener {
    fun addArticle(article: Article)
    fun removeArticle(id : Int)
}