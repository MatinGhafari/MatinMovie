package ir.matin.matinfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.matin.matinfilm.data.model.PopularResponse
import ir.matin.matinfilm.data.repository.HomeRepository
import ir.matin.matinfilm.utils.network.NetworkRequest
import ir.matin.matinfilm.utils.network.NetworkResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel(){

    init {
        viewModelScope.launch{

            delay(150)
            getPopular()
            getTopRated()
            getTrending()
        }
    }
    //popular =>
    private val _popularData = MutableLiveData<NetworkRequest<PopularResponse>>()
    val popularData: LiveData<NetworkRequest<PopularResponse>> = _popularData

    //top_rated =>
        private val _topRatedData= MutableLiveData<NetworkRequest<PopularResponse>>()
        val topRatedData: LiveData<NetworkRequest<PopularResponse>> =_topRatedData

    // trending =>
    private val _trendingData= MutableLiveData<NetworkRequest<PopularResponse>>()
    val trendingData : LiveData<NetworkRequest<PopularResponse>> = _trendingData

    fun getPopular() =viewModelScope.launch{
        _popularData.value = NetworkRequest.Loading()
        val result =repository.getPopular()
        _popularData.value = NetworkResponse(result).generateResponse()



    }

    fun getTopRated()=viewModelScope.launch{
        _topRatedData.value = NetworkRequest.Loading()
        val result =repository.getTopRated()
        _topRatedData.value= NetworkResponse(result).generateResponse()
    }

    fun getTrending()=viewModelScope.launch{
        _trendingData.value = NetworkRequest.Loading()
        val result =repository.getTrending()
        _trendingData.value = NetworkResponse(result).generateResponse()


    }








}