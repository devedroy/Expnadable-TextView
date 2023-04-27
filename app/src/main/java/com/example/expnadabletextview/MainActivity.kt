package com.example.expnadabletextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.view.View
import android.view.View.OnTouchListener
import android.view.animation.AnimationUtils
import com.example.expnadabletextview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        binding.tv.startAnimation(zoomIn)
        binding.tv.setOnTouchListener(object : OnTouchListener {
            private var scale = 1f
            private val gestureDetector =
                ScaleGestureDetector(this@MainActivity, object : SimpleOnScaleGestureListener() {
                    override fun onScale(detector: ScaleGestureDetector): Boolean {
                        scale *= detector.scaleFactor
                        binding.tv.scaleX = scale
                        binding.tv.scaleY = scale
                        return true
                    }

                })

            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                p1?.let { gestureDetector.onTouchEvent(it) }
                return true
            }

        })
    }
}