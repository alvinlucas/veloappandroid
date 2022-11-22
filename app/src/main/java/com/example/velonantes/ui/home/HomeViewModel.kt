package com.example.velonantes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.velonantes.model.Station

class HomeViewModel : ViewModel() {

    private val _stations = MutableLiveData<List<Station>>().apply {
        value = ArrayList()
    }
    val stations: MutableLiveData<List<Station>> = _stations
}