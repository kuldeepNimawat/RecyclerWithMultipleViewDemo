package com.kuldeep.recyclerwithmultipleviewdemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuldeep.recyclerwithmultipleviewdemo.data.api.Resource
import com.kuldeep.recyclerwithmultipleviewdemo.data.model.HomeRecyclerViewItem
import com.kuldeep.recyclerwithmultipleviewdemo.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
private val repository: Repository
): ViewModel() {
    private val _homeListItemLiveData = MutableLiveData<Resource<List<HomeRecyclerViewItem>>>()
    val homeListItemsLiveData : LiveData<Resource<List<HomeRecyclerViewItem>>>
    get()=_homeListItemLiveData

    init{
        getHomeListItem()
    }

    private fun getHomeListItem()=viewModelScope.launch{
            _homeListItemLiveData.postValue(Resource.loading)

        val movieDeferred= async{ repository.getMovies()}
        val directorDeferred= async{ repository.getDirectors()}

        val movies=movieDeferred.await()
        val directors=directorDeferred.await()

        val homeItemsList= mutableListOf<HomeRecyclerViewItem>()
        if(movies is Resource.Success && directors is Resource.Success){
            homeItemsList.add(HomeRecyclerViewItem.Title(1,"Recommended Movies"))
            homeItemsList.addAll(movies.value)
            homeItemsList.add(HomeRecyclerViewItem.Title(2, "Top Directors"))
            homeItemsList.addAll(directors.value)
            _homeListItemLiveData.postValue(Resource.Success(homeItemsList))
        }else{
            Resource.Failure(false,null,null)
        }
    }
}