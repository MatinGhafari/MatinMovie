package ir.matin.matinfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.matin.matinfilm.data.model.PopularResponse
import ir.matin.matinfilm.data.repository.SearchRepository
import ir.matin.matinfilm.utils.network.NetworkRequest
import ir.matin.matinfilm.utils.network.NetworkResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    private val _searchData = MutableLiveData<NetworkRequest<PopularResponse>>()
    val searchData: LiveData<NetworkRequest<PopularResponse>> = _searchData


    fun getSearch(movieName: String) {
        viewModelScope.launch {
            _searchData.value = NetworkRequest.Loading()
            val result = repository.getSearch(movieName)
            _searchData.value = NetworkResponse(result).generateResponse()
        }


    }

}