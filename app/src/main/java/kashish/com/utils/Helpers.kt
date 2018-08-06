package kashish.com.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.view.Window
import kashish.com.utils.DateUtils.Companion.getDateFromEpoch
import kashish.com.utils.Urls.Companion.MOVIE_DETAILS_BASE_URL
import kashish.com.utils.Urls.Companion.TMDB_API_KEY
import java.text.ParseException
import java.text.SimpleDateFormat
import android.R.id.edit
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE
import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.view.WindowManager
import kashish.com.utils.DateUtils.Companion.getYear
import java.util.*


/**
 * Created by Kashish on 01-08-2018.
 */
object Helpers {

    fun buildUpcomingMoviesUrl(pageNumber: Int, adult: String = "false"): String{
        return "https://api.themoviedb.org/3/movie/upcoming?api_key="+ TMDB_API_KEY+
                "&language=en-US"+"&page="+pageNumber+"&region=US|IN|UK"+"&with_release_type=2|3"
    }

    fun buildTopRatedMoviesUrl(pageNumber: Int, adult: String = "false"): String{
        return Urls.BASE_URL +"movie/top_rated?api_key="+TMDB_API_KEY+
                "&language=en-US&page="+pageNumber
    }

    fun buildNowShowingMoviesUrl(pageNumber: Int, adult: String = "false"): String{
        return Urls.BASE_URL +"movie/now_playing?api_key="+TMDB_API_KEY+
                "&language=en-US&page="+pageNumber+"&region=US|IN|UK"
    }

    fun buildPopularMoviesUrl(pageNumber: Int, adult: String = "false"): String{
        return Urls.BASE_URL +"movie/popular?api_key="+TMDB_API_KEY+
                "&language=en-US&page="+pageNumber+"&region=US|IN|UK"
    }

    fun buildMovieCastUrl(movieId: String): String{
        return "https://api.themoviedb.org/3/movie/"+movieId+"/credits?api_key="+ TMDB_API_KEY
    }

    fun buildProfileImageUrl(path: String): String {
        return "http://image.tmdb.org/t/p/w185" + path
    }

    fun buildImageUrl(path: String): String {
        return "http://image.tmdb.org/t/p/w342" + path
    }

    fun buildBackdropImageUrl(path: String): String {
        return "http://image.tmdb.org/t/p/w780" + path
    }

    fun buildYouTubeThumbnailURL(key: String): String {
        return "https://img.youtube.com/vi/$key/0.jpg"
    }

    fun buildYoutubeURL(key: String): String {
        return "https://www.youtube.com/watch?v=" + key
    }

    fun buildMovieDetailUrl(movieId: String): String {
        return MOVIE_DETAILS_BASE_URL + movieId + "?api_key=" + TMDB_API_KEY + "&append_to_response=videos"
    }

    fun buildMovieReviewUrl(movieId: String, pageNumber: Int): String {
        return MOVIE_DETAILS_BASE_URL + movieId + "/reviews?api_key=" + TMDB_API_KEY + "&language=en-US&page=" + pageNumber
    }

    fun buildDiscoverMovieUrl(pageNumber: Int,
                              sortBy: String = "release_date.asc",
                              adult: String = "false",
                              isPrimaryReleaseYearIncluded:Boolean = false,
                              primaryReleaseYear:String = getYear(System.currentTimeMillis()),
                              getReleaseDateLess:Boolean = false,
                              getReleaseDateGreater:Boolean = true,
                              releaseDateLessThan:String = getDateFromEpoch(System.currentTimeMillis()),
                              releaseDateGreaterThan:String = getDateFromEpoch(System.currentTimeMillis()-31536000000)
            ): String{
        var url:String = "https://api.themoviedb.org/3/discover/movie?api_key=" + TMDB_API_KEY +
                "&language=en-US&page=" + pageNumber+
                "&region=US|IN&sort_by="+ sortBy +"&include_adult="+ adult +"&include_video=true"

        if (isPrimaryReleaseYearIncluded){
            url+="&primary_release_year="+primaryReleaseYear
        }

        if (getReleaseDateGreater){
            url+="&release_date.gte="+releaseDateGreaterThan
        } else if (getReleaseDateLess) {
            url+="&release_date.lte="+releaseDateLessThan
        }

        return url
    }


    fun handleViewHideOnScroll(view: View, dy: Int, maxTranslation: Float) {
        val translationY = view.translationY
        if (dy > 0) {
            if (translationY < maxTranslation) {
                if (translationY + dy < maxTranslation) {
                    view.translationY = translationY + dy
                } else {
                    view.translationY = maxTranslation
                }
            }
        } else {
            if (translationY > 0) {
                if (translationY + dy > 0) {
                    view.translationY = translationY + dy
                } else {
                    view.translationY = 0f
                }
            }
        }
    }

    fun setUpTransparentStatusBar(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }


    fun getDefaultLanguageCode():String{
        return Locale.getDefault().language
    }
    fun getDefaultCountryCode():String{
        return Locale.getDefault().country
    }

}
