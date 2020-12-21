package net.jcunningham.android.spiredigital.moviedb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.jcunningham.android.spiredigital.moviedb.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    //private lateinit var requestQueue: RequestQueue
    private val TAG = "MAIN_ACTIVITY"
    private val JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=ade6e5277bfd242f810ddacd925148b0"

    private lateinit var binding : ActivityMainBinding
    //private lateinit var viewModel : MainActivityViewModel
    private var movies: ArrayList<Movie> = ArrayList()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //
        // RecyclerView setup
        //
        val recyclerView = binding.movieList
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager


        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        movieAdapter = MovieAdapter(this, movies)
        recyclerView.adapter = movieAdapter




        //
        // Networking setup
        //
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieService::class.java)
        val call = service.getPopularMovies(getString(R.string.api_key))

        call.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(call: Call<PopularMovieResponse>, response: Response<PopularMovieResponse>) {
                if (response.code() == 200) {
                    movieAdapter.setItems(response.body()!!.results)
                    movieAdapter.notifyDataSetChanged()
                }
                binding.progressBar.setVisibility(View.GONE)
            }
            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Log.d("TEST", "Error")
                binding.progressBar.setVisibility(View.GONE)
            }
        })
    }

    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)

        binding.movieList.layoutManager = LinearLayoutManager(this.applicationContext)
        binding.movieList.itemAnimator = DefaultItemAnimator()

    }



    fun selectMovie(movie:Movie) {

        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("id", movie.id)
        intent.putExtra("title", movie.title)
        intent.putExtra("overview", movie.overview)
        intent.putExtra("poster_path", movie.poster_path)
        intent.putExtra("release_date", movie.release_date)
        intent.putExtra("vote_average", movie.vote_average)

        startActivity(intent)
    }

}