package com.paulvarry.cartoparty.page.map

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.android.libraries.maps.model.PolylineOptions
import com.google.maps.android.ktx.awaitMap
import com.paulvarry.cartoparty.ui.rememberMapViewWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MapPage(navController: NavHostController, slug: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "coucou")
        Text(text = "coucou")
        Text(text = "coucou")
        Text(text = "coucou")
        Text(text = "coucou")
        Box(modifier = Modifier.weight(1f)) { Map() }
        Button(modifier = Modifier.padding(16.dp), onClick = { /*TODO*/ }) {
            Text("Add")
        }
    }
}


@Composable
private fun Map() {
    val mapViewLifecycle = rememberMapViewWithLifecycle()

    AndroidView({ mapViewLifecycle }) { mapView ->
        CoroutineScope(Dispatchers.Main).launch {
            val ctx = mapView.context

            val map = mapView.awaitMap()
            map.uiSettings.isZoomControlsEnabled = true

            if (ContextCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
            ) {
                map.isMyLocationEnabled = true
                map.uiSettings.isMyLocationButtonEnabled = true
            }

            val pickUp = LatLng(-35.016, 143.321)
            val destination = LatLng(-32.491, 147.309)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 6f))
            val markerOptions = MarkerOptions()
                .title("Sydney Opera House")
                .position(pickUp)
            map.addMarker(markerOptions)

            val markerOptionsDestination = MarkerOptions()
                .title("Restaurant Hubert")
                .position(destination)
            map.addMarker(markerOptionsDestination)

            map.addPolyline(
                PolylineOptions().add(
                    pickUp,
                    LatLng(-34.747, 145.592),
                    LatLng(-34.364, 147.891),
                    LatLng(-33.501, 150.217),
                    LatLng(-32.306, 149.248),
                    destination
                )
            )
        }
    }
}
