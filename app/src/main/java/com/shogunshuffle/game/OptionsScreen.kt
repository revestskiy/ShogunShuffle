package com.shogunshuffle.game

import android.graphics.fonts.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shogunshuffle.game.ui.theme.nujnoefont



@Composable
fun OptionsScreen(
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            )
    ) {

        Image(
            painter = painterResource(id = R.drawable.backbutton),
            contentDescription = "Back Button",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable {
                    onBack()
                }
        )

        Text(
            text = "OPTIONS",
            fontFamily = nujnoefont,
            fontSize = 38.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(width = 390.dp, height = 280.dp)
                .paint(painter = painterResource(id = R.drawable.backgroundsetting), contentScale = ContentScale.Fit)
        ) {
            // Значения для ползунков громкости музыки и звука
            var musicVolume by remember { mutableStateOf(0.5f) }
            var soundVolume by remember { mutableStateOf(0.5f) }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                SliderItem(
                    title = "MUSIC",
                    value = musicVolume,
                    onValueChange = { musicVolume = it }
                )

                Spacer(modifier = Modifier.height(16.dp))


                SliderItem(
                    title = "SOUND",
                    value = soundVolume,
                    onValueChange = { soundVolume = it }
                )
            }
        }
    }
}

@Composable
fun SliderItem(
    title: String,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


        Text(
            text = title,
            fontFamily = nujnoefont,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

        // Ползунок для изменения громкости
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..1f, // Диапазон громкости от 0 до 1
            colors = SliderDefaults.colors(
                thumbColor = Color.White, // Цвет бегунка
                activeTrackColor = Color.Blue // Цвет активной полосы ползунка
            ),
            modifier = Modifier.weight(2f)
        )
    }
}

