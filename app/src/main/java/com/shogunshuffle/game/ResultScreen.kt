package com.shogunshuffle.game

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shogunshuffle.game.ui.theme.nujnoefont
import kotlinx.coroutines.delay

@Preview
@Composable
fun ResultScreen() {
    // Отложенное действие для перехода
    LaunchedEffect(Unit) {
        delay(2000)
        // Здесь можно добавить действия через 2 секунды (например, переход)
    }

    // Основной контейнер
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.backgroundlastscreen),
                contentScale = ContentScale.Crop
            ),
        contentAlignment = Alignment.Center // Центровка элементов
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Центровка по горизонтали
            verticalArrangement = Arrangement.SpaceBetween, // Распределение элементов
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 336.dp) // Отступы по краям
        ) {
            // "GAME OVER" в верхней части экрана
            OutlinedText(
                text = "GAME OVER \n" +
                        "score 250",
                fontSize = 28.sp,
                textColor = Color.White,
                outlineColor = Color(0xFF53B4F7),
                fontFamily = nujnoefont,
            )

            // Кнопки Try Again и Home внизу экрана
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tryagain), // Ресурс кнопки Try Again
                    contentDescription = "Try Again",
                    modifier = Modifier
                        .size(240.dp, 70.dp)
                        .clickable {
                            // Действие при нажатии кнопки Try Again
                        }
                )
                Spacer(modifier = Modifier.height(50.dp))

                Image(
                    painter = painterResource(id = R.drawable.home), // Ресурс кнопки Home
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(250.dp, 60.dp)
                        .clickable {
                            // Действие при нажатии кнопки Home
                        }
                )
            }

            Spacer(modifier = Modifier.height(50.dp)) // Отступ снизу
        }
    }
}
