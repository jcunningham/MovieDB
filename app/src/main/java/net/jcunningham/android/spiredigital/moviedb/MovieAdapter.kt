package net.jcunningham.android.spiredigital.moviedb

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MovieAdapter(private var context: Context, private var movies: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val thumbnailUrlFormat =  context.getString(R.string.movie_thumbnail_format)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.movie_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.titleTextView.text = movies[position].title

        Picasso.get().load(String.format(thumbnailUrlFormat, movies[position].poster_path)).into(viewHolder.imageView)

        viewHolder.itemView.setOnClickListener {
            val mainActivity = context as MainActivity
            mainActivity.selectMovie(movies[position]);
        }


    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val titleTextView: TextView = view.findViewById(R.id.movieDetailsTitle)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    fun setItems(items: ArrayList<Movie>) {
        movies = items
    }

}