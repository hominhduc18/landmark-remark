package com.example.landmarkremark.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.landmarkremark.util.LogCat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

abstract class BaseFragment:Fragment(),OnMapReadyCallback {

    private var bundle: Bundle? =null

    lateinit var fragView: View

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        if (bundle !=null){
            super.onCreate(bundle)
        }else
            super.onCreate(savedInstanceState)
            LogCat.d("Lifecycle in Fragment onCreate " + this.javaClass.name)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(initIdLayoutFragment(), container,false)
        fragView = view
        LogCat.d("Lifecycle in Fragment onCreateView " + this.javaClass.name)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogCat.d("Lifecycle in Fragment onViewCreated " + this.javaClass.name)
        initView()
        clickView()

    }
    abstract fun initIdLayoutFragment() : Int
    abstract fun initView()
    abstract fun clickView()

    override fun onPause() {
        super.onPause()
        LogCat.d("Lifecycle in Fragment onPause " + this.javaClass.name)
    }

    override fun onStop() {
        super.onStop()
        LogCat.d("Lifecycle in Fragment onStop " + this.javaClass.name)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogCat.d("Lifecycle in Fragment onDestroy " + this.javaClass.name)
    }

    override fun onStart() {
        super.onStart()
        LogCat.d("Lifecycle in Fragment onStart " + this.javaClass.name)
    }

    override fun onResume() {
        super.onResume()
        LogCat.d("Lifecycle in Fragment onResume " + this.javaClass.name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogCat.d("Lifecycle in Fragment onDestroyView " + this.javaClass.name)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        onCustomMapReady(googleMap)
    }

    abstract fun onCustomMapReady(googleMap: GoogleMap)

}