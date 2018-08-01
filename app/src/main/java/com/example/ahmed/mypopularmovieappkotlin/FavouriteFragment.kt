package com.example.ahmed.mypopularmovieappkotlin

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FavouriteFragment:android.support.v4.app.Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_movies, container, false)  // Inflate the layout for this fragment
    }

}