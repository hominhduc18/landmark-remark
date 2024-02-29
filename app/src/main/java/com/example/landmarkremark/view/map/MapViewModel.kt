package com.example.landmarkremark.view.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MapViewModel : ViewModel() {

    private val _noteSaved = MutableLiveData<Boolean>()
    val noteSaved: LiveData<Boolean>
        get() = _noteSaved

    fun saveUserNoteFirebase(note: String, latitude: Double, longitude: Double) {
        _noteSaved.value = true
    }
}

