package com.example.ahmed.mypopularmovieappkotlin

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.annotations.SerializedName

data class MovieResponse(
        @field:SerializedName("results")
        val results: List<MovieModule>? = null
)

data class TrailerResponse(
        @field:SerializedName("results")
        val results: List<TrailerModule>? = null
)

data class ReviewResponse(
        @field:SerializedName("results")
        val results: List<ReviewModule>? = null
)




@Entity(tableName = "movies")
data class MovieModule(

        @field:SerializedName("vote_count")
        @ColumnInfo(name = "movie_rating") val voteCount: String? = "",

        @field:SerializedName("id")
        @field:PrimaryKey var id: Long = 0,

        @field:SerializedName("vote_average")
        @Ignore var voteAvg: String? = "",

        @field:SerializedName("title")
        val title: String? = "",

        @field:SerializedName("poster_path")
        @ColumnInfo(name = "movie_poster") var posterPath: String? = "",

        @field:SerializedName("overview")
        @ColumnInfo(name = "movie_overview") val overview: String? = "",

        @field:SerializedName("release_date")
        @Ignore val relDate: String? = "",

        @field:SerializedName("backdrop_path")
        @Ignore val backdropPath: String? = ""





)


@Entity(tableName = "review",
        foreignKeys = arrayOf(ForeignKey(
                entity = MovieModule::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("movie_id"),
                onDelete = CASCADE,
                onUpdate = CASCADE
        )),
        indices = arrayOf(Index("movie_id")))
data class ReviewModule(
        @ColumnInfo(name = "movie_id") val movieId: Int,
        @field:SerializedName("author")
        val reviewAuthor: String? = "",
        @field:SerializedName("content")
        val reviewContent: String? = "",
        @field:SerializedName("url")
        val reviewUrl: String? = "",
        @PrimaryKey(autoGenerate = true) val id: Long = 0
)

@Entity(tableName = "trailer",
        foreignKeys = arrayOf(ForeignKey(
                entity = MovieModule::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("movie_id"),
                onDelete = CASCADE,
                onUpdate = CASCADE
        )),
        indices = arrayOf(Index("movie_id")))
data class TrailerModule(
        @ColumnInfo(name = "movie_id") val movieId: Int,
        @field:SerializedName("key")
        val keyTrailers: String? = "",
        @PrimaryKey(autoGenerate = true) val id: Long = 0
)


