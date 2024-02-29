package com.example.landmarkremark.view.noteMap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.landmarkremark.model.NoteMarker
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class NotesMapViewModel : ViewModel() {

    private val _noteMarkers = MutableLiveData<List<NoteMarker>>()
    val noteMarkers: LiveData<List<NoteMarker>>
        get() = _noteMarkers

    private val database = FirebaseDatabase.getInstance().reference.child("notes")
    private val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    init {
        if (currentUserUid.isNotEmpty()) {
            retrieveUserNotesFromFirebase()
        } else {
            retrieveAllNotesFromFirebase()
        }
    }

    private fun retrieveUserNotesFromFirebase() {
        database.orderByChild("userId").equalTo(currentUserUid).addListenerForSingleValueEvent(object : ValueEventListener {
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
                Log.e("Firebase", "Database error occurred: ${error.message}")
            }
        })
    }

    private fun retrieveAllNotesFromFirebase() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val markers = mutableListOf<NoteMarker>()
                for (noteSnapshot in snapshot.children) {
                    val note = noteSnapshot.child("note").value.toString()
                    val latitude = noteSnapshot.child("latitude").value.toString().toDouble()
                    val longitude = noteSnapshot.child("longitude").value.toString().toDouble()
                    val userName = noteSnapshot.child("userName").value.toString()
                    val noteLatLng = LatLng(latitude, longitude)
                    markers.add(NoteMarker(noteLatLng, note, userName))
                }
                _noteMarkers.value = markers
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Database error occurred: ${error.message}")

            }
        })
    }
}
