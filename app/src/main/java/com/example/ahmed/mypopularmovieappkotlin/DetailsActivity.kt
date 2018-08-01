package com.example.ahmed.mypopularmovieappkotlin

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ahmed.mypopularmovieappkotlin.adapter.ReviewAdapter
import com.example.ahmed.mypopularmovieappkotlin.adapter.TrailersAdapter
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    var id: String? = ""
    var title: String? = ""
    var overview: String? = ""
    var poster: String? = ""
    var voteAvg: String? = ""
    var voteCount: String? = ""
    var releaseDate: String? = ""
    var backDropPath: String? = ""
     var isFavorite:Boolean?=false
    private lateinit var movie: MovieModule
    private lateinit var traileradapter: TrailersAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private var trailerList = mutableListOf<TrailerModule>()
    private var reviewList = mutableListOf<ReviewModule>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        getExtraIntent()
        setUpIntentViews()
        getTrailers()
        getReviews()
    }

    private fun getReviews() {
        MyApplication.api.getReviews(this!!.id!!).enqueue(object : Callback<ReviewResponse> {
            override fun onFailure(call: Call<ReviewResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<ReviewResponse>?, response: Response<ReviewResponse>?) {
                if (response != null) {
                    response.body()!!.results.let {
                        if (it != null) {
                            reviewList.addAll(it)
                        }
                        reviewAdapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }

    private fun getTrailers() {
        MyApplication.api.getTrailers(this.id!!).enqueue(object : Callback<TrailerResponse> {
            override fun onFailure(call: Call<TrailerResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<TrailerResponse>?, response: Response<TrailerResponse>?) {
                if (response != null) {
                    response.body()?.results.let {
                        if (it != null) {
                            trailerList.addAll(it)
                        }
                        traileradapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }

    private fun setUpIntentViews() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolBar.setExpandedTitleColor(Color.TRANSPARENT)

        movieTitle.text = title
        movieDate.text = releaseDate
        rateMovie.text = voteAvg
        tvOverview.text = overview

        Glide.with(this).load(Constant.BASE_PHOTO_PATH + poster).into(image_poster)
        Glide.with(this).load(Constant.BASE_PHOTO_PATH + backDropPath).into(image_backDrop)

        traileradapter = TrailersAdapter(trailerList)
        rvTrailers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvTrailers.adapter = traileradapter

        reviewAdapter = ReviewAdapter(reviewList)
        rvReviews.layoutManager = LinearLayoutManager(this)
        rvReviews.adapter = reviewAdapter

    }

    private fun getExtraIntent() {
        id = intent.getStringExtra(Constant.DATA_ID)
        title = intent.getStringExtra(Constant.DATA_TITLE)
        overview = intent.getStringExtra(Constant.DATA_OVERVIEW)
        poster = intent.getStringExtra(Constant.DATA_POSTER)
        voteAvg = intent.getStringExtra(Constant.DATA_VOTE_AVG)
        voteCount = intent.getStringExtra(Constant.DATA_VOTE_COUNT)
        releaseDate = intent.getStringExtra(Constant.DATA_RELEASE_DATE)
        backDropPath = intent.getStringExtra(Constant.DATA_BACKDROP)
    }

}
