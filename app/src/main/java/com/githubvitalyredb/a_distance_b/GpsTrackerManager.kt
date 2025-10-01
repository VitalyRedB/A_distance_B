package com.githubvitalyredb.a_distance_b

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

private val gson = Gson()
private val JSON = "application/json; charset=utf-8".toMediaType()
private val CLIENT = OkHttpClient()

class GpsTrackerManager(
    private val token: String,
    // Теперь userId - это общий ID приложения/трекера
    private val userId: String
) {
    private val url = "https://redburngpscontrol.pythonanywhere.com/api/add_point"

    /**
     * Отправляет GPS-координаты на сервер в фоновом потоке.
     * @param lat Широта
     * @param lon Долгота
     * @param pointId Идентификатор точки (например, "PointA" или "PointB"), который станет user_id в запросе.
     */
    fun sendGpsPoint(lat: Double, lon: Double, pointId: String) { // ДОБАВЛЕНО pointId
        // Запускаем корутину для выполнения сетевого запроса вне главного потока
        GlobalScope.launch(Dispatchers.IO) {
            val dateTime = getCurrentDateTime()

            val gpsPoint = GpsPoint(
                token = token,
                user_id = pointId, // ИСПОЛЬЗУЕМ pointId как user_id
                date = dateTime.first,
                time = dateTime.second,
                lat = lat,
                lon = lon
            )
            // ... (остальной код отправки)

            val jsonBody = gson.toJson(gpsPoint)

            val body = jsonBody.toRequestBody(JSON)
            val request = Request.Builder()
                .url(url)
                .post(body)
                .build()

            try {
                CLIENT.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        println("Failed to post point: ${response.code} ${response.message}")
                    } else {
                        println("GPS point successfully sent. User_ID: $pointId. Response: ${response.body?.string()}")
                    }
                }
            } catch (e: IOException) {
                println("Network error while sending GPS point: ${e.message}")
            }
        }
    }

    // метод getCurrentDateTime для получения текущенго времени и даты
    private fun getCurrentDateTime(): Pair<String, String> {
        return Pair(Utils.getCurrentDateFormatted(), Utils.getCurrentTimeFormatted())
    }
}