package net.jcunningham.android.spiredigital.moviedb

import com.google.gson.annotations.SerializedName

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

//data class Movie(val id:Int, val title:String) {
//    var thumbnail:String = ""
//    var overview:String = ""
//    var vote_average:Int = 0
//    //var genres:Array<Int> = Array<Int>()
//}