package com.example.yandexmapkit

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.material.card.MaterialCardView
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class MainActivity : AppCompatActivity() {

    lateinit var mapKit:MapKit
    lateinit var mapView:MapView
    lateinit var btnTraffic:MaterialCardView
    private var trafficOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("be6f6d14-e009-404d-ab6e-c2eb4956a7fc")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)
        requestLocationPermission()
        initFields()
        startMaps()
        showTrafficLayer()
        getLocationUser()
    }

    private fun initFields() {
        mapKit = MapKitFactory.getInstance()
        mapView = findViewById(R.id.mapView)
        btnTraffic = findViewById(R.id.btnTraffic)
    }

    private fun startMaps() {
        mapView.map.move(
            CameraPosition(Point(45.035120, 38.977334), 17.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 10f), null)
    }

    private fun showTrafficLayer() {
        val trafficLayer = mapKit.createTrafficLayer(mapView.mapWindow)
        btnTraffic.setOnClickListener {
            when (trafficOn) {
                false -> {
                    trafficOn = true
                    trafficLayer.isTrafficVisible = false
                }
                true -> {
                    trafficOn = false
                    trafficLayer.isTrafficVisible = true
                }
            }
        }
    }

    private fun getLocationUser() {
        val locationUser = mapKit.createUserLocationLayer(mapView.mapWindow)
        locationUser.isVisible = true
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),0)
            return
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }
}