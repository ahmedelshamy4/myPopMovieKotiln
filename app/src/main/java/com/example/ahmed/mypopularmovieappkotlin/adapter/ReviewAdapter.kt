package com.example.ahmed.mypopularmovieappkotlin.adapter

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ahmed.mypopularmovieappkotlin.R
import com.example.ahmed.mypopularmovieappkotlin.ReviewModule
import kotlinx.android.synthetic.main.review_view.view.*

class ReviewAdapter(private val reviewList: List<ReviewModule>) : RecyclerView.Adapter<ReviewAdapter.myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.review_view, parent, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
     holder.bind(position)
    }

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val context = itemView.context
            val reviewResult: ReviewModule = reviewList[position]
            itemView.tv_Author.text = reviewResult.reviewAuthor
            itemView.tv_Content.text = reviewResult.reviewContent
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(reviewResult.reviewUrl))
            context.startActivity(intent)
        }

    }
}