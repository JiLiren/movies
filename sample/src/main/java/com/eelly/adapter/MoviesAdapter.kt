package com.eelly.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.eelly.R
import com.eelly.core.tools.TransitionHelper
import com.eelly.holder.MoviesHolder
import com.eelly.model.MovieBean
import com.eelly.view.activity.DetailsActivity

/**
 * @author Vurtne on 20-Nov-17.
 *
 * @param mMovies 电影
 */
class MoviesAdapter(private var mMovies:ArrayList<MovieBean>,var context: Context) : Adapter<MoviesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MoviesHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movies, parent, false)
        return MoviesHolder(view)
    }

    override fun getItemCount(): Int {
        return if (mMovies.isEmpty()) 0 else mMovies.size
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        val bean : MovieBean = mMovies[position]
        holder.mNameTv.text = bean.title
        holder.mRateRb.setStar(bean.rating.stars / 10)

        var strName = ""
        bean.casts.forEach { actor ->
            strName += actor.name + " / "
        }
        if (strName.endsWith(" / ")){
            strName = strName.substring(0,strName.length-2)
        }
        holder.mActorTv.text = context.getString(R.string.text_actor,strName)

        var strDirector = ""
        bean.directors.forEach { actor ->
            strDirector += actor.name + " / "
        }
        if (strDirector.endsWith(" / ")){
            strDirector = strDirector.substring(0,strDirector.length-2)
        }
        holder.mDirectorTv.text = context.getString(R.string.text_director,strDirector)

        Glide.with(context).load(bean.images.large).
                into(holder.mBannerIv)

        holder.mClickLayout.setOnClickListener({
            view ->
            val intent  = Intent(context,DetailsActivity::class.java)
            val pairs = TransitionHelper.createSafeTransitionParticipants(
                    context as Activity, false,
                    Pair<View, Any>(view, context.getString(R.string.transitionName_details)))
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    context as Activity, *pairs)
//            val bundle = Bundle()
//            bundle.putParcelable("data",bean)
//            intent.putExtras(bundle)
            ActivityCompat.startActivity(context, intent, compat.toBundle())
        })
    }

    fun addBean(movies:MutableList<MovieBean>){
//        mMovies.addAll(movies)
        notifyDataSetChanged()
    }


}

