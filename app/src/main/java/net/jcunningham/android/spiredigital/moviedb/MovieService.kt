package net.jcunningham.android.spiredigital.moviedb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("popular?")
    fun getPopularMovies(@Query("api_key") api_key: String): Call<PopularMovieResponse>
}