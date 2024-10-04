package com.shogunshuffle.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LevelsScreen(
    onMenu: () -> Unit,
    onLevel: (Int) -> Unit
) {
    val levelButtons = listOf(
        R.drawable.lvl1 to 1, R.drawable.lvl2 to 2, R.drawable.lvl3 to 3,
        R.drawable.lvl4 to 4, R.drawable.lvl5 to 5, R.drawable.lvl6 to 6,
        R.drawable.lvl7 to 7, R.drawable.lvl8 to 8, R.drawable.lvl9 to 9,
        R.drawable.lvl10 to 10, R.drawable.lvl11 to 11, R.drawable.lvl12 to 12,
        R.drawable.lvl13 to 13, R.drawable.lvl14 to 14, R.drawable.lvl15 to 15,
        R.drawable.lvl16 to 16, R.drawable.lvl17 to 17, R.drawable.lvl18 to 18,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background), // Задний фон
                contentScale = ContentScale.Crop
            )
    ) {
        // Кнопка "Назад"
        Image(
            painter = painterResource(id = R.drawable.menubutton), // Заменить на ваш ресурс
            contentDescription = "Back Button",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.TopStart)
                .padding(16.dp)
                .clickable {
                    onMenu()
                }
        )

        // Заголовок "Levels"
        Image(
            painter = painterResource(id = R.drawable.levelsbutton), // Ваш заголовок "Levels"
            contentDescription = "Levels",
            modifier = Modifier
                .size(220.dp, 80.dp)
                .align(Alignment.TopCenter)
                .padding(top = 20.dp)
        )


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp) // Отступ между строками
        ) {
            items(levelButtons.chunked(2)) { levelPair ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    LevelButton(levelResId = levelPair[0].first) {
                        onLevel(levelPair[0].second)
                    }

                    if (levelPair.size > 1) {
                        LevelButton(levelResId = levelPair[1].first) {
                            onLevel(levelPair[1].second)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LevelButton(levelResId: Int, onClick: () -> Unit = {}) {
    Image(
        painter = painterResource(id = levelResId),
        contentDescription = "Level Button",
        modifier = Modifier
            .size(150.dp, 60.dp) // Увеличенный размер для более вытянутого вида
            .clickable {
                onClick()
            }
    )
}