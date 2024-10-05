package com.shogunshuffle.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shogunshuffle.game.ui.theme.nujnoefont

val Result.isWin: Boolean
    get() = this is Result.Win

@Composable
fun ResultScreen(
    result: Result,
    onNextOrTryAgain: () -> Unit,
    onBack: () -> Unit
) {
    val gradient = listOf(
        Color.Transparent,
        Color(0xFFF63B00)
    )
    // Основной контейнер
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            ),
        contentAlignment = Alignment.Center // Центровка элементов
    ) {
        Box(modifier = Modifier.background(brush = Brush.verticalGradient(gradient)))
        Column(
            modifier = Modifier
                .background(
                    color = Color.Black.copy(alpha = 0.5f),
                )
            ,
            horizontalAlignment = Alignment.CenterHorizontally, // Центровка по горизонтали
            verticalArrangement = Arrangement.Center, // Распределение элементов // Отступы по краям
        ) {
            // "GAME OVER" в верхней части экрана
            if (result.isWin) {
                Image(
                    painter = painterResource(id = R.drawable.ic_stars),
                    contentDescription = null,
                    modifier = Modifier
                        .width(200.dp)
                        .height(90.dp)
                )
            }
            OutlinedText(
                text = buildAnnotatedString {
                    append("${result.phrase} \n")
                            withStyle(style = SpanStyle(fontSize = 20.sp)) {
                                append("SCORE\n")
                            }
                    append("${result.score}")
                },
                fontSize = 28.sp,
                textColor = Color.White,
                outlineColor = Color(0xFF53B4F7),
                fontFamily = nujnoefont,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))
            // Кнопки Try Again и Home внизу экрана
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = result.btnDrawableId), // Ресурс кнопки Try Again
                    contentDescription = "",
                    modifier = Modifier
                        .size(240.dp, 70.dp)
                        .clickable {
                            onNextOrTryAgain()
                        }
                )
                Spacer(modifier = Modifier.height(20.dp))

                Image(
                    painter = painterResource(id = R.drawable.home), // Ресурс кнопки Home
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(250.dp, 60.dp)
                        .clickable {
                            onBack()
                        }
                )
            }

            Spacer(modifier = Modifier.height(50.dp)) // Отступ снизу
        }
    }
}
