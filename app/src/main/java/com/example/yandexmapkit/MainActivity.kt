package com.example.yandexmapkit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class MainActivity : AppCompatActivity() {

    lateinit var mapView:MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("be6f6d14-e009-404d-ab6e-c2eb4956a7fc")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)
        mapView.map.move(CameraPosition(Point(45.035120, 38.977334), 11.0f,0.0f,0.0f),
        Animation(Animation.Type.SMOOTH,300f),null)
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