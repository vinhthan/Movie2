package com.thanmanhvinh.movieandnews.data.api


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieSearchRequest(
    @SerializedName("api_key")
    var apiKey: String,
    @SerializedName("query")
    var query: String
)

data class MovieSearch(
    var page: Int,
    var results: MutableList<Result>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
) {
    data class Result(
        var adult: Boolean,
        @SerializedName("backdrop_path")
        var backdropPath: String,
        @SerializedName("genre_ids")
        var genreIds: List<Int>,
        var id: Int,
        @SerializedName("original_language")
        var originalLanguage: String,
        @SerializedName("original_title")
        var originalTitle: String,
        var overview: String,
        var popularity: Double,
        @SerializedName("poster_path")
        var posterPath: String,
        @SerializedName("release_date")
        var releaseDate: String,
        var title: String,
        var video: Boolean,
        @SerializedName("vote_average")
        var voteAverage: Int,
        @SerializedName("vote_count")
        var voteCount: Int
    ): Serializable {
        fun getImagePosterPath (): String{
            return "https://image.tmdb.org/t/p/w500/$posterPath"
        }
    }
}