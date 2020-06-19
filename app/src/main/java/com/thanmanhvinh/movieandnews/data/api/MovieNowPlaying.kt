package com.thanmanhvinh.movieandnews.data.api


import com.google.gson.annotations.SerializedName

data class MovieNowPlaying(
    @SerializedName("dates")
    var dates: Dates,
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: MutableList<Results>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
) {
    data class Dates(
        var maximum: String,
        var minimum: String
    )

    data class Results(
        var adult: Int,
        @SerializedName("genre_ids")
        var genreIds: List<Int>,
        var id: Int,
        @SerializedName("original_language")
        var originalLanguage: String,
        @SerializedName("original_title")
        var originalTitle: String,
        @SerializedName("overview")
        var overview: String,
        @SerializedName("popularity")
        var popularity: Double,
        @SerializedName("poster_path")
        var posterPath: String,
        @SerializedName("release_date")
        var releaseDate: String,
        @SerializedName("title")
        var title: String,
        @SerializedName("backdrop_path")
        var backdropPath: String,
        @SerializedName("video")
        var video: Boolean,
        @SerializedName("vote_average")
        var voteAverage: Double,
        @SerializedName("vote_count")
        var voteCount: Int
    ){
        fun getImageBackdropPathNowPlaying(): String {
            return "https://image.tmdb.org/t/p/w500/" + backdropPath
        }
        fun getImagePosterPathNowPlaying(): String {
            return "https://image.tmdb.org/t/p/w500/" + posterPath
        }

    }
}