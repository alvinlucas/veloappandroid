package com.example.velonantes.ui.park

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.velonantes.model.Park

class ParkViewModel : ViewModel() {

    private val _park = MutableLiveData<List<Park>>().apply {
        value = ArrayList()
    }
    val parks: MutableLiveData<List<Park>> = _park
}