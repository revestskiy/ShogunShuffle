package com.shogunshuffle.game

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel: ViewModel() {
    private val _result = MutableStateFlow<Result>(Result.Idle)

    val result = _result.asStateFlow()

    fun onResult(result: Result) {
        _result.value = result
    }
}

sealed class Result(open val score: Int, val phrase: String, @DrawableRes val btnDrawableId: Int,
    open val lvl: Int
    ) {

    data class Win(override val score: Int, override val lvl: Int): Result(score, "GREAT!!!", R.drawable.next, lvl)

    data class Lose(override val score: Int, override val lvl: Int): Result(score, "GAME OVER", R.drawable.tryagain, lvl)

    data object Idle: Result(0, "", -1, -1)
}