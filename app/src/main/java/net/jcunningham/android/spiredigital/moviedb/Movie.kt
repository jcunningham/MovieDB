package net.jcunningham.android.spiredigital.moviedb

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Movie {
    @SerializedName("genre_ids")
    var genre_ids = arrayOf<Int>()
    @SerializedName("id")
    var id = 0
    @SerializedName("title")
    var title = ""
    @SerializedName("overview")
    var overview = ""
    @SerializedName("poster_path")
    var poster_path = ""
    @SerializedName("release_date")
    var release_date = ""
    @SerializedName("vote_average")
    var vote_average:Float = 0.0f
}
