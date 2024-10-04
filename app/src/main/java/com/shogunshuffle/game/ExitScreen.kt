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
import androidx.compose.foundation.layout.width
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
fun ExitScreen(
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Центрируем содержимое
        ) {

            // Текст "EXIT" в верхней части экрана
            OutlinedText(
                text = "EXIT",
                fontSize = 48.sp,
                textColor = Color.White,
                outlineColor = Color(0xFF53B4F7),
                fontFamily = nujnoefont
            )

            Spacer(modifier = Modifier.height(128.dp))


            OutlinedText(
                text = "ARE YOU SURE?",
                fontSize = 28.sp,
                textColor = Color.White,
                outlineColor = Color(0xFF53B4F7),
                fontFamily = nujnoefont,
            )

            Spacer(modifier = Modifier.height(48.dp)) // Отступ перед кнопками

            // Кнопки "YES" и "NO"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.yesbutton),
                    contentDescription = "Yes Button",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {

                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.nobutton),
                    contentDescription = "No Button",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            onBack()
                        }
                )
            }
        }
    }
}
