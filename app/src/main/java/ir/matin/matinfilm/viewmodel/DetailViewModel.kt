package ir.matin.matinfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hilt_aggregated_deps._ir_matin_matinfilm_viewmodel_DetailViewModel_HiltModules_BindsModule
import ir.matin.matinfilm.data.model.DetailResponse
import ir.matin.matinfilm.data.repository.DetailRepository
import ir.matin.matinfilm.utils.network.NetworkRequest
import ir.matin.matinfilm.utils.network.NetworkResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {

    private val _detailData = MutableLiveData<NetworkRequest<DetailResponse>>()
    val detailData: LiveData<NetworkRequest<DetailResponse>> = _detailData



    fun getMovieDetail(id : Int)= viewModelScope.launch {

        _detailData.value = NetworkRequest.Loading()
        val result = repository.getMovieDetail(id)
        _detailData.value = NetworkResponse(result).generateResponse()


    }






}