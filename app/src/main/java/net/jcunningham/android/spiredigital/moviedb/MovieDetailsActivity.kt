package net.jcunningham.android.spiredigital.moviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import net.jcunningham.android.spiredigital.moviedb.databinding.ActivityMovieDetailsBinding


class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMovieDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        val extras = intent.extras

        binding.movieDetailsTitle.text = extras!!.getString("title")
        binding.movieDetailsOverview.text = extras!!.getString("overview")
        binding.movieDetailsReleaseDate.text = extras!!.getString("release_date")
        binding.movieDetailsRating.text = String.format(getString(R.string.movie_rating_format, extras.getFloat("vote_average")))
        val thumbnailUrl = String.format(
            getString(R.string.movie_thumbnail_format), extras!!.getString("poster_path")
        )
        Picasso.get().load(thumbnailUrl).into(binding.movieDetailsThumbnail)
    }
}
