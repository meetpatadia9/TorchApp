package com.ipsmeet.torchapp.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ipsmeet.torchapp.R
import com.ipsmeet.torchapp.ui.theme.TorchAppTheme

@Composable
fun MainScreen(isLightOn: (Boolean) -> Unit, isSosOn: (Boolean) -> Unit) {
    var isTorchOn by rememberSaveable { mutableStateOf(false) }
    var torchImg by rememberSaveable { mutableIntStateOf(R.drawable.torch_off) }
    var sosOn by rememberSaveable { mutableStateOf(false) }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Image(
                painter = painterResource(id = torchImg),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        isTorchOn = !isTorchOn
                        torchImg = if (isTorchOn) R.drawable.torch_on else R.drawable.torch_off
                        isLightOn(isTorchOn)
                    }
            )
        }   // Column
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Button(
                onClick = {
                    sosOn = !sosOn
                    isSosOn(sosOn)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (sosOn) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.errorContainer,
                    contentColor = if (sosOn) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.error
                ),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.padding(30.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 5.dp,
                )
            ) {
                Text(
                    text = "SOS",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 2.sp
                )
            }   // Button
        }   // Column
    }   // Box
}

@Preview
@Composable
private fun PreviewMainScreen() {
    TorchAppTheme {
        MainScreen({}, {})
    }
}