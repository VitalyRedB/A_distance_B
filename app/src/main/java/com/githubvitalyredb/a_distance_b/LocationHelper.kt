package com.githubvitalyredb.a_distance_b

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationHelper(private val context: Context, private val activity: Activity) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    // Callback-интерфейс для передачи координат обратно в MainActivity
    interface LocationCallback {
        fun onLocationReceived(location: Location)
    }

    fun requestLocation(callback: LocationCallback) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Если разрешения нет, запрашиваем его у пользователя
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
            Toast.makeText(context, "Предоставьте разрешение на местоположение", Toast.LENGTH_SHORT).show()
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                // Передаем полученные координаты через callback-интерфейс
                callback.onLocationReceived(location)
            } else {
                Toast.makeText(context, "Не удалось получить местоположение. Попробуйте снова.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val REQUEST_LOCATION_PERMISSION = 1
    }
}