package com.kuldeep.recyclerwithmultipleviewdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuldeep.recyclerwithmultipleviewdemo.R
import com.kuldeep.recyclerwithmultipleviewdemo.data.model.HomeRecyclerViewItem
import com.kuldeep.recyclerwithmultipleviewdemo.databinding.ItemDirectorBinding
import com.kuldeep.recyclerwithmultipleviewdemo.databinding.ItemMovieBinding
import com.kuldeep.recyclerwithmultipleviewdemo.databinding.ItemTitleBinding
import java.lang.IllegalArgumentException

class HomeRecyclerViewAdapter : RecyclerView.Adapter<HomeRecyclerViewHolder>() {

    var items= listOf<HomeRecyclerViewItem>()
    set(value){
        field=value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        return when(viewType){

            R.layout.item_title -> HomeRecyclerViewHolder.TitleViewHolder(
                ItemTitleBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            ))

            R.layout.item_movie -> HomeRecyclerViewHolder.MovieViewHolder(
                ItemMovieBinding.inflate(LayoutInflater.from(parent.context),
                    parent,
                    false
                ))

            R.layout.item_director -> HomeRecyclerViewHolder.DirectorViewHolder(
                ItemDirectorBinding.inflate(LayoutInflater.from(parent.context),
                    parent,
                    false
                ))

            else -> throw IllegalArgumentException("Invalid viewtype provided")

        }
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        when(holder){
            is HomeRecyclerViewHolder.TitleViewHolder->holder.bind(items[position] as HomeRecyclerViewItem.Title)
            is HomeRecyclerViewHolder.MovieViewHolder->holder.bind(items[position] as HomeRecyclerViewItem.Movie)
            is HomeRecyclerViewHolder.DirectorViewHolder->holder.bind(items[position] as HomeRecyclerViewItem.Director)
        }
    }

    override fun getItemCount()=items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is HomeRecyclerViewItem.Director -> R.layout.item_director
            is HomeRecyclerViewItem.Movie -> R.layout.item_movie
            is HomeRecyclerViewItem.Title -> R.layout.item_title
            else -> throw IllegalArgumentException("Invalid viewtype provided")
        }
    }
}