package com.mubeendroid.flashdroid

import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mubeendroid.flashdroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isFlashlightOn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        binding.apply {
            imgFlashlight.setOnClickListener {
                if (!isFlashlightOn) {
                    imgFlashlight.setImageResource(R.drawable.ic_flashlight_on)
                } else {
                    imgFlashlight.setImageResource(R.drawable.ic_flashlight_off)
                }
                isFlashlightOn = !isFlashlightOn
                try {
                    cameraManager.setTorchMode(cameraManager.cameraIdList[0], isFlashlightOn)
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}