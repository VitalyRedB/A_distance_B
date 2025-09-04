package com.githubvitalyredb.a_distance_b

import android.view.MotionEvent
import android.view.View

class ImageButtonAnimator {

    /**
     * Устанавливает слушатель касаний для ImageView, чтобы создать эффект нажатой кнопки.
     * @param view ImageView, к которому применяется анимация.
     * @param clickListener Обработчик клика, который будет вызван после отпускания кнопки.
     */
    fun setAnimation(view: View, clickListener: () -> Unit) {
        view.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Анимация уменьшения при касании
                    v.animate()
                        .scaleX(0.9f)
                        .scaleY(0.9f)
                        .setDuration(100)
                        .start()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // Анимация возвращения к исходному размеру при отпускании
                    v.animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(100)
                        .start()
                    // Вызываем переданный обработчик клика
                    clickListener.invoke()
                }
            }
            // Возвращаем false, чтобы onTouchEvent был вызван после OnTouchListener
            false
        }
    }
}