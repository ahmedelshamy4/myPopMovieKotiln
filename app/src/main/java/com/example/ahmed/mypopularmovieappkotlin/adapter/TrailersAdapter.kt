package com.example.ahmed.mypopularmovieappkotlin.adapter

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.ahmed.mypopularmovieappkotlin.R
import com.example.ahmed.mypopularmovieappkotlin.TrailerModule
import kotlinx.android.synthetic.main.trailer_view.view.*

class TrailersAdapter(private val trailerList: List<TrailerModule>) : RecyclerView.Adapter<TrailersAdapter.myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.trailer_view, parent, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {

        return trailerList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val context = itemView.context
            val trailerResult: TrailerModule = trailerList[position]

            Glide.with(context)
                    .load("http://img.youtube.com/vi/${trailerResult.keyTrailers}/hqdefault.jpg")
                    .into(itemView.image_thumbnail)

            itemView.image_thumbnail.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=${trailerResult.keyTrailers}"))
                context.startActivity(intent)
            }

        }
    }
}