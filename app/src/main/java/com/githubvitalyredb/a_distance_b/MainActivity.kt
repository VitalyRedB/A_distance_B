package com.githubvitalyredb.a_distance_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.media.MediaPlayer
import android.location.Location
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var backgroundMusicPlayer: MediaPlayer? = null
    private lateinit var locationHelper: LocationHelper
    private var locationA: Location? = null
    private var locationB: Location? = null
    // Используем ImageView, так как это картинки, а не кнопки
    private lateinit var buttonA_Image: ImageView
    private lateinit var buttonB_Image: ImageView
    private lateinit var locationATextView: TextView
    private lateinit var locationBTextView: TextView
    private lateinit var distanceTextView: TextView
    // Создаем экземпляр нашего класса для анимации
    private lateinit var imageButtonAnimator: ImageButtonAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация UI-элементов
        buttonA_Image = findViewById(R.id.button_a_image)
        buttonB_Image = findViewById(R.id.button_b_image)
        locationATextView = findViewById(R.id.location_a_textview)
        locationBTextView = findViewById(R.id.location_b_textview)
        distanceTextView = findViewById(R.id.distance_textview)

        // Инициализация LocationHelper и ImageButtonAnimator
        locationHelper = LocationHelper(this, this)
        imageButtonAnimator = ImageButtonAnimator()

        // Применяем анимацию к кнопке Point A
        imageButtonAnimator.setAnimation(buttonA_Image) {
            playSound(R.raw.data_sound)
            locationATextView.text = "Please wait!"
            // ИСПОЛЬЗУЕМ НОВЫЙ МЕТОД и ПРАВИЛЬНОЕ ИМЯ ИНТЕРФЕЙСА
            locationHelper.startLocationUpdates(object : LocationHelper.OnLocationReceivedCallback {
                override fun onLocationReceived(location: Location) {
                    locationA = location
                    playSound(R.raw.click_sound)
                    locationATextView.text = "Lat: ${String.format("%.4f", location.latitude)}, Lon: ${String.format("%.4f", location.longitude)}"
                    calculateDistance()
                    locationHelper.stopLocationUpdates() // Важно: останавливаем обновления после получения данных
                }
            })
        }

        // Применяем анимацию к кнопке Point B
        imageButtonAnimator.setAnimation(buttonB_Image) {
            playSound(R.raw.data_sound)
            locationBTextView.text = "Please wait!"
            // ИСПОЛЬЗУЕМ НОВЫЙ МЕТОД и ПРАВИЛЬНОЕ ИМЯ ИНТЕРФЕЙСА
            locationHelper.startLocationUpdates(object : LocationHelper.OnLocationReceivedCallback {
                override fun onLocationReceived(location: Location) {
                    locationB = location
                    playSound(R.raw.click_sound)
                    locationBTextView.text = "Lat: ${String.format("%.4f", location.latitude)}, Lon: ${String.format("%.4f", location.longitude)}"
                    calculateDistance()
                    locationHelper.stopLocationUpdates() // Важно: останавливаем обновления после получения данных
                }
            })
        }

        // Запуск анимации для developerImageView
        val developerImageView: ImageView = findViewById(R.id.developerImageView)
        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
        developerImageView.startAnimation(scaleAnimation)
    }

    override fun onResume() {
        super.onResume()
        if (backgroundMusicPlayer == null) {
            backgroundMusicPlayer = MediaPlayer.create(this, R.raw.background_music)
            backgroundMusicPlayer?.isLooping = true
        }
        backgroundMusicPlayer?.start()
    }

    override fun onPause() {
        super.onPause()
        backgroundMusicPlayer?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        backgroundMusicPlayer?.stop()
        backgroundMusicPlayer?.release()
        backgroundMusicPlayer = null
    }

    private fun calculateDistance() {
        if (locationA != null && locationB != null) {
            val distanceInMeters = locationA!!.distanceTo(locationB!!)
            distanceTextView.text = "${String.format("%.2f", distanceInMeters)} м"
        }
    }

    private fun playSound(resId: Int) {
        val soundEffectPlayer = MediaPlayer.create(this, resId)
        soundEffectPlayer.setOnCompletionListener { mp -> mp.release() }
        soundEffectPlayer.start()
    }
}