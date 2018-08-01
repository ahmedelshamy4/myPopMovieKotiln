package com.example.ahmed.mypopularmovieappkotlin.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

import com.example.ahmed.mypopularmovieappkotlin.Constant
import com.example.ahmed.mypopularmovieappkotlin.DetailsActivity
import com.example.ahmed.mypopularmovieappkotlin.MovieModule
import com.example.ahmed.mypopularmovieappkotlin.R
import kotlinx.android.synthetic.main.movie_view.view.*

class MovieAdapter(private val movieList: List<MovieModule>) : RecyclerView.Adapter<MovieAdapter.myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.movie_view, parent, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val context = itemView.context
            val movieResult: MovieModule = movieList[position]
            itemView.tv_poster_image.text = movieResult.title//get textView
            Glide.with(context)
                    .load(Constant.BASE_PHOTO_PATH + movieResult.posterPath)
                    .into(itemView.poster_image)

            itemView.tv_poster_image.setOnClickListener {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra(Constant.DATA_ID, movieResult.id)
                intent.putExtra(Constant.DATA_TITLE, movieResult.title)
                intent.putExtra(Constant.DATA_POSTER, movieResult.posterPath)
                intent.putExtra(Constant.DATA_BACKDROP, movieResult.backdropPath)
                intent.putExtra(Constant.DATA_OVERVIEW, movieResult.overview)
                intent.putExtra(Constant.DATA_VOTE_AVG, movieResult.voteAvg)
                intent.putExtra(Constant.DATA_VOTE_COUNT, movieResult.voteCount)
                intent.putExtra(Constant.DATA_RELEASE_DATE, movieResult.relDate)

                context.startActivity(intent)
            }
        }

    }
}