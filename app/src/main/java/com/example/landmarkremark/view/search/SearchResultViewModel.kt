package com.example.landmarkremark.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.landmarkremark.model.NoteMarker
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.*

class SearchResultViewModel : ViewModel() {
    private val _noteMarkers = MutableLiveData<List<NoteMarker>>()
    val noteMarkers: LiveData<List<NoteMarker>> get() = _noteMarkers

    private lateinit var database: DatabaseReference

    fun init() {
        database = FirebaseDatabase.getInstance().reference.child("notes")
    }

    fun searchNotes(keyword: String) {
        val query = database.orderByChild("note").startAt(keyword).endAt(keyword + "\uf8ff")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val markers = mutableListOf<NoteMarker>()
                for (noteSnapshot in snapshot.children) {
                    val note = noteSnapshot.child("note").value.toString()
                    val latitude = noteSnapshot.child("latitude").value.toString().toDouble()
                    val longitude = noteSnapshot.child("longitude").value.toString().toDouble()
                    val noteLatLng = LatLng(latitude, longitude)
                    markers.add(NoteMarker(noteLatLng, note))
                }
                _noteMarkers.value = markers
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}
