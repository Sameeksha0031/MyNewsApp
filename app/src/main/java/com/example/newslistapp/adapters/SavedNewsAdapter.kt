package com.example.newslistapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.newslistapp.R
import com.example.newslistapp.model.Article

class SavedNewsAdapter(val context : Context, val list : List<Article>) : RecyclerView.Adapter<SavedNewsAdapter.SavedNewsAdapterViewHolder>() {

    var removeOperation : RemoveOperation ?= null
    class SavedNewsAdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var heading: TextView = itemView.findViewById(R.id.newTitleSaved)
        var subTitle: TextView = itemView.findViewById(R.id.newsDescriptionSaved)
        var outerCardLayout : CardView = itemView.findViewById(R.id.outerCardLayoutSaved)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(context)
            .inflate(R.layout.cardlayout_for_saved_articles, parent, false)
        return SavedNewsAdapterViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: SavedNewsAdapterViewHolder, position: Int) {
        val holderList = list[position]
        holder.heading.text = holderList.title
        holder.subTitle.text = holderList.content

        holder.outerCardLayout.setOnLongClickListener(object : OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {
                removeOperation?.removeSavedNews(holderList.id)
                return true
            }
        })

    }

    fun removedArticled(removeOperation: RemoveOperation) {
        this.removeOperation = removeOperation
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

interface RemoveOperation {
    fun removeSavedNews(id : Int)
}