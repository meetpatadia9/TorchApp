package com.ipsmeet.torchapp

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ipsmeet.torchapp.ui.layout.MainScreen
import com.ipsmeet.torchapp.ui.theme.TorchAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var cameraManager: CameraManager
    private var cameraID = "0"

    private lateinit var sosHandler: Handler

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        cameraID = cameraManager.cameraIdList[0]

        sosHandler = Handler(Looper.getMainLooper())

        setContent {
            TorchAppTheme {
                MainScreen(
                    isLightOn = { isTorchOn ->
                        if (isTorchOn) {
                            turnOnFlashLight()
                        } else {
                            turnOffFlashLight()
                        }
                    },
                    isSosOn = { isSosOn ->
                        mainViewModel.sosOn.value = isSosOn
                        if (isSosOn) {
                            toggleSos()
                        } else {
                            turnOffSos()
                        }
                    }
                )   // MainScreen
            }   // TorchAppTheme
        }   // setContent
    }

    private fun turnOnFlashLight() {
        cameraManager.setTorchMode(cameraID, true)
    }

    private fun turnOffFlashLight() {
        cameraManager.setTorchMode(cameraID, false)
    }

    private fun toggleSos() {
        cameraManager.setTorchMode(cameraID, mainViewModel.sosOn.value)
        mainViewModel.updateSosState()
        sosHandler.postDelayed({ toggleSos() }, 500)
    }

    private fun turnOffSos() {
        mainViewModel.sosOn.value = false
        mainViewModel.updateTorchImg()
        sosHandler.removeCallbacksAndMessages(null)
        cameraManager.setTorchMode(cameraID, false)
    }
}
