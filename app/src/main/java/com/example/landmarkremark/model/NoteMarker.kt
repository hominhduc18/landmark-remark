package com.example.landmarkremark.model

import com.google.android.gms.maps.model.LatLng

data class NoteMarker(
    val location: LatLng,
    val note: String,
    val userName: String = "")
