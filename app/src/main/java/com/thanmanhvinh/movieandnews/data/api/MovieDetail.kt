package com.thanmanhvinh.movieandnews.data.api


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDetailRequest (
    @SerializedName("api_key")
    var apiKey: String
)

data class MovieDetail(
    var adult: Boolean,
    @SerializedName("backdrop_path")
    var backdropPath: String,
    @SerializedName("belongs_to_collection")
    var belongsToCollection: BelongsToCollection,
    var budget: Int,
    var genres: List<Genre>,
    var homepage: String,
    var id: Int,
    @SerializedName("imdb_id")
    var imdbId: String,
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("original_title")
    var originalTitle: String,
    var overview: String,
    var popularity: Double,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountry>,
    @SerializedName("release_date")
    var releaseDate: String,
    var revenue: Int,
    var runtime: Int,
    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage>,
    var status: String,
    var tagline: String,
    var title: String,
    var video: Boolean,
    @SerializedName("vote_average")
    var voteAverage: Double,
    @SerializedName("vote_count")
    var voteCount: Int
) : Serializable {
    data class BelongsToCollection(
        @SerializedName("backdrop_path")
        var backdropPath: String,
        var id: Int,
        var name: String,
        @SerializedName("poster_path")
        var posterPath: String
    )

    data class Genre(
        var id: Int,
        var name: String
    )

    data class ProductionCompany(
        var id: Int,
        @SerializedName("logo_path")
        var logoPath: String,
        var name: String,
        @SerializedName("origin_country")
        var originCountry: String
    )

    data class ProductionCountry(
        @SerializedName("iso_3166_1")
        var iso31661: String,
        var name: String
    )

    data class SpokenLanguage(
        @SerializedName("iso_639_1")
        var iso6391: String,
        var name: String
    )

    fun getImageBackdropPath(): String{
        return "https://image.tmdb.org/t/p/w500/$backdropPath"
    }

    fun getImagePosterPath(): String{
        return "https://image.tmdb.org/t/p/w500/$posterPath"
    }
}