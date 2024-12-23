package ir.matin.matinfilm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.matin.matinfilm.utils.network.NetworkStatusLiveData
import javax.inject.Inject

@HiltViewModel
class NetworkStatusViewModel @Inject constructor(
    networkStatusLiveData: NetworkStatusLiveData
) : ViewModel() {
    val networkStatus = networkStatusLiveData
}