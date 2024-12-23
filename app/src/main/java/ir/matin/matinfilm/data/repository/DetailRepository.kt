package ir.matin.matinfilm.data.repository

import ir.matin.matinfilm.data.network.ApiService
import javax.inject.Inject

class DetailRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovieDetail(id:Int)=apiService.getMovieDetail(id)

}