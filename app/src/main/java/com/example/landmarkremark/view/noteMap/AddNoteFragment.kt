package com.example.landmarkremark.view.noteMap
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.landmarkremark.R
import com.example.landmarkremark.base.BaseFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class NotesMapFragment : BaseFragment() {
    private lateinit var mMap: GoogleMap
    private lateinit var viewModel: NotesMapViewModel
    private lateinit var noteSearchButton: Button
    override fun initIdLayoutFragment(): Int = R.layout.fragment_add_note
    override fun initView() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapnoteFragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        viewModel = ViewModelProvider(this).get(NotesMapViewModel::class.java)

        viewModel.noteMarkers.observe(viewLifecycleOwner, Observer { noteMarkers ->
            noteMarkers.forEach { marker ->
                addNoteMarker(marker.location, marker.note, marker.userName)
            }
        })
        noteSearchButton = fragView.findViewById<Button>(R.id.noteSearchButton)
        noteSearchButton.setOnClickListener {
            clickView()
        }
    }
    override fun clickView() {
        findNavController().navigate(R.id.action_notesMapFragment_to_searchResultFragment)
    }
    override fun onCustomMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }
    private fun addNoteMarker(location: LatLng, note: String, userName: String = "") {
        val marker = mMap.addMarker(MarkerOptions().position(location).title(note))
        if (userName.isNotEmpty()) {
            marker?.snippet = "User: $userName"
        }
    }
}
