package com.example.ahmed.mypopularmovieappkotlin

import android.app.AlertDialog
import android.app.Fragment
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahmed.mypopularmovieappkotlin.adapter.MovieAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularFragment : android.support.v4.app.Fragment() {
    private val movieList = mutableListOf<MovieModule>()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        getData()
        return inflater.inflate(R.layout.fragment_movies, container, false)  // Inflate the layout for this fragment
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        adapter = MovieAdapter(movieList)
        rvMovie.layoutManager = GridLayoutManager(activity, 2)
        rvMovie.setHasFixedSize(true)
        rvMovie.adapter = adapter
    }

    private fun getData() {

        MyApplication.api.getPopular().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                Log.e(tag, t?.message)
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                Log.i(tag, "data: ${Gson().toJsonTree(response?.body())}")

                if (response != null) {
                    response.body()!!.results.let {
                        if (it != null) {
                            movieList.addAll(it)
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }
}