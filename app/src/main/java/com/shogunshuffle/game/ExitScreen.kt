package com.shogunshuffle.game
import androidx.activity.ComponentActivity
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shogunshuffle.game.ui.theme.nujnoefont

@Composable
fun rememberActivity(): ComponentActivity {
    val context = LocalContext.current
    return remember { context as ComponentActivity }
}
@Composable
fun ExitScreen(
    onBack: () -> Unit
) {
    val activity = rememberActivity()
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
                textAlign = TextAlign.Center
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
                            activity.finishAndRemoveTask()
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
