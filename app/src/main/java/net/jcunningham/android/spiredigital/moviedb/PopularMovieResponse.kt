package net.jcunningham.android.spiredigital.moviedb

import com.google.gson.annotations.SerializedName

class PopularMovieResponse {
    @SerializedName("page")
    var page: Int? = 0
    @SerializedName("results")
    var results = ArrayList<Movie>()
    @SerializedName("total_pages")
    var totalPages: Int? = 0
    @SerializedName("total_results")
    var totalResults: Int? = 0
}
