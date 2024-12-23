package ir.matin.matinfilm.data.repository

import ir.matin.matinfilm.data.network.ApiService
import ir.matin.matinfilm.utils.POPULAR_PAGE
import javax.inject.Inject

class HomeRepository @Inject constructor( private val apiService: ApiService) {

   suspend fun getPopular()=apiService.getPopular(POPULAR_PAGE)

   suspend fun getTopRated()=apiService.getTopRated()

   suspend fun getTrending()=apiService.getTrending()


}