package com.example.list.ui.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.core.model.MovieModel
import com.example.core.network.Constant
import com.example.list.databinding.NewsLayoutBinding



class MovieAdapter( val onclick : (MovieModel) -> Unit) : ListAdapter<MovieModel, MovieAdapter.MovieViewHolder>(
    Callback
){

    inner class MovieViewHolder (var rowView: NewsLayoutBinding) : RecyclerView.ViewHolder(rowView.root) {

        fun onBind(item:MovieModel){
            rowView.tvTitle.text = item.title

            Glide.with(itemView.context)
                .load(Constant.TMDB_IMAGEURL + item.posterPath)
                .placeholder(getLoading(itemView.context))
                .into(rowView.ivMovie)

            itemView.setOnClickListener {
                onclick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val viewBinding=
            NewsLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }


    companion object {
        private val Callback = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean =
                oldItem == newItem
        }
    }
}



// get progressbar as drawable
fun getLoading(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    return circularProgressDrawable
}