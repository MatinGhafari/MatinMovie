package ir.matin.matinfilm.data.repository

import ir.matin.matinfilm.data.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getSearch(movieName: String)=apiService.getSearch(movieName)

}

