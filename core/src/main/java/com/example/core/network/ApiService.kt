package com.example.core.network




import com.example.core.model.MovieResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getRequest(@Query("api_key") apiKey:String,) : Observable<MovieResponse>

    @GET("movie/popular")
    suspend fun getRequestCoro(@Query("api_key") apiKey:String,) : Response<MovieResponse>
}