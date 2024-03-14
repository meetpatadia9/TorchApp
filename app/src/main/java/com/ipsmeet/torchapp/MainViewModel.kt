package com.ipsmeet.torchapp

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var isTorchOn = mutableStateOf(value = false)
    var sosOn = mutableStateOf(value = false)
    var torchImg = mutableIntStateOf(value = R.drawable.torch_off)

    fun updateTorchState() {
        isTorchOn.value = !isTorchOn.value
    }

    fun updateSosState() {
        sosOn.value = !sosOn.value

        if (sosOn.value) {
            torchImg.intValue = R.drawable.torch_off
        } else {
            torchImg.intValue = R.drawable.torch_on
        }
    }

    fun updateTorchImg() {
        if (isTorchOn.value) {
            torchImg.intValue = R.drawable.torch_on
        } else {
            torchImg.intValue = R.drawable.torch_off
        }
    }

}