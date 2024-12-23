package ir.matin.matinfilm.data.network

import ir.matin.matinfilm.data.model.DetailResponse
import ir.matin.matinfilm.data.model.PopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopular(@Query("page") page: Int): Response<PopularResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): Response<DetailResponse>

    @GET("search/movie")
    suspend fun getSearch(@Query("query") movieName: String): Response<PopularResponse>

    @GET("movie/top_rated")
    suspend fun getTopRated(): Response<PopularResponse>

    @GET("trending/movie/day")
    suspend fun getTrending(): Response<PopularResponse>

}