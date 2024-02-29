package com.example.landmarkremark.view.search

import android.annotation.SuppressLint
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.landmarkremark.R
import com.example.landmarkremark.base.BaseFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SearchResultFragment : BaseFragment() {

    private lateinit var userSearchEditText: EditText
    private lateinit var noteSearchButton: Button
    private lateinit var mMap: GoogleMap
    private lateinit var viewModel: SearchResultViewModel

    override fun initIdLayoutFragment(): Int = R.layout.fragment_search_result
    @SuppressLint("UseRequireInsteadOfGet")
    override fun initView() {
        userSearchEditText = view!!.findViewById(R.id.searchEditText)
        noteSearchButton = view!!.findViewById(R.id.searchButton)
        viewModel = ViewModelProvider(this).get(SearchResultViewModel::class.java)
        noteSearchButton.setOnClickListener {
            val searchText = userSearchEditText.text.toString()
            if (searchText.isNotEmpty()) {
                viewModel.searchNotes(searchText)
            } else {
                Toast.makeText(requireContext(), "Please enter a search keyword", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.noteMarkers.observe(viewLifecycleOwner) { noteMarkers ->
            noteMarkers?.let {
                for (marker in noteMarkers) {
                    addNoteMarker(marker.location, marker.note)
                }
            }
        }
    }
    override fun clickView() {
        // Handle click events if needed
    }
    override fun onCustomMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }
    private fun addNoteMarker(location: LatLng, note: String) {
        mMap.addMarker(MarkerOptions().position(location).title(note))
    }
}
