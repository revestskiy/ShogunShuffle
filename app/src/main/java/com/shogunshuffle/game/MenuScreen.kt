package com.shogunshuffle.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shogunshuffle.game.ui.theme.nujnoefont


@Composable
fun MenuScreen(
    onLevels: () -> Unit,
    onOptions: () -> Unit,
    onExit: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedText(
                text = "MENU",
                fontSize = 48.sp,
                textColor = Color.White,
                outlineColor = Color(0xFF53B4F7), // Контурный цвет
                fontFamily = nujnoefont // Ваш шрифт
            )

            Spacer(modifier = Modifier.height(52.dp))

            Image(
                painter = painterResource(id = R.drawable.startbutton),
                contentDescription = "Exit Button",
                modifier = Modifier
                    .size(width = 250.dp, height = 80.dp)
                    .clickable {
                        onLevels()
                    }
            )

            Spacer(modifier = Modifier.height(52.dp))


            Image(
                painter = painterResource(id = R.drawable.optionsbutton),
                contentDescription = "Exit Button",
                modifier = Modifier
                    .size(width = 250.dp, height = 80.dp)
                    .clickable {
                        onOptions()
                    }
            )
            Spacer(modifier = Modifier.height(52.dp))


            Image(
                painter = painterResource(id = R.drawable.exitbutton),
                contentDescription = "Exit Button",
                modifier = Modifier
                    .size(width = 250.dp, height = 80.dp)
                    .clickable {
                        onExit()
                    }
            )
        }
    }
}

@Composable
fun OutlinedText(
    text: CharSequence,
    fontSize: TextUnit,
    textColor: Color,
    outlineColor: Color,
    fontFamily: FontFamily,
    textAlign: TextAlign = TextAlign.Start
) {
    // Смещения для создания контура
    val outlineOffset = 2.dp

    Box(
        contentAlignment = Alignment.Center
    ) {
        // Текст с смещениями для имитации контура
        when (text) {
            is AnnotatedString -> {
                Text(
                    text = text,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    color = outlineColor,
                    modifier = Modifier.offset(x = outlineOffset, y = outlineOffset),
                    textAlign = textAlign
                )
            }

            is String -> {
                Text(
                    text = text,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    color = outlineColor,
                    modifier = Modifier.offset(x = outlineOffset, y = outlineOffset),
                    textAlign = textAlign
                )
            }
        }
        when (text) {
            is AnnotatedString -> {
                Text(
                    text = text,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    color = outlineColor,
                    modifier = Modifier.offset(x = -outlineOffset, y = outlineOffset),
                    textAlign = textAlign
                )
            }

            is String -> {
                Text(
                    text = text,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    color = outlineColor,
                    modifier = Modifier.offset(x = -outlineOffset, y = outlineOffset),
                    textAlign = textAlign
                )
            }
        }

        when (text) {
            is AnnotatedString -> {
                Text(
                    text = text,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    color = outlineColor,
                    modifier = Modifier.offset(x = outlineOffset, y = -outlineOffset),
                    textAlign = textAlign
                )
            }

            is String -> {
                Text(
                    text = text,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    color = outlineColor,
                    modifier = Modifier.offset(x = outlineOffset, y = -outlineOffset),
                    textAlign = textAlign
                )
            }
        }
        when (text) {
            is AnnotatedString -> {
                Text(
                    text = text,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    color = outlineColor,
                    modifier = Modifier.offset(x = -outlineOffset, y = -outlineOffset),
                    textAlign = textAlign
                )
            }

            is String -> {
                Text(
                    text = text,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    color = outlineColor,
                    modifier = Modifier.offset(x = -outlineOffset, y = -outlineOffset),
                    textAlign = textAlign
                )
            }
        }

        // Основной текст поверх контура
        when (text) {
            is AnnotatedString -> {
                Text(
                    text = text,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    color = textColor,
                    textAlign = textAlign
                )
            }

            is String -> {
                Text(
                    text = text,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    color = textColor,
                    textAlign = textAlign
                )
            }
        }
    }
}