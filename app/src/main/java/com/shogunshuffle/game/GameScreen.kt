    package com.shogunshuffle.game

    import androidx.activity.compose.BackHandler
    import androidx.compose.animation.AnimatedVisibility
    import androidx.compose.animation.core.animateFloatAsState
    import androidx.compose.animation.fadeIn
    import androidx.compose.animation.fadeOut
    import androidx.compose.animation.scaleIn
    import androidx.compose.animation.scaleOut
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.interaction.MutableInteractionSource
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.BoxWithConstraints
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
    import androidx.compose.material.ripple.rememberRipple
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableIntStateOf
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.alpha
    import androidx.compose.ui.draw.paint
    import androidx.compose.ui.draw.scale
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.unit.Dp
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.lifecycle.viewmodel.compose.viewModel
    import com.shogunshuffle.game.ui.theme.nujnoefont
    import kotlinx.coroutines.delay
    import kotlin.math.sqrt
    import kotlin.random.Random

    typealias All = Any?

    infix fun All.not(any: All) = this != any
    inline infix fun <reified T> T?.or(any: T?) = this ?: any
    @Composable
    fun GameScreen(
        level: Int,
        onBack: () -> Unit,
        onResult: () -> Unit,
        viewModel: GameViewModel = viewModel()
    ) {
        var score by remember { mutableIntStateOf(0) }
        var isGameOver by remember { mutableStateOf(false) }
        var elements by remember { mutableStateOf(listOf<GameElement>()) }
        var timeRemaining by remember { mutableIntStateOf(60) }
        var isGameRunning by remember { mutableStateOf(false) }
        var explodingElements by remember { mutableStateOf(listOf<GameElement>()) }
        var isSettingsVisible by remember { mutableStateOf(false) }
        // Timer logic
        var scoreEffects by remember { mutableStateOf(listOf<Pair<Int, GameElement>>()) } // Store score effects
        LaunchedEffect(isGameRunning) {
            if (isGameRunning) {
                while (timeRemaining > 0 && isGameRunning) {
                    delay(1000L)
                    if (isSettingsVisible not true) {
                        timeRemaining--
                    }
                }
                if (timeRemaining == 0) {
                    isGameRunning = false
                    isGameOver = true
                    if (score < level * 100) {
                        viewModel.onResult(Result.Lose(score, level))
                    }
                    else {
                        viewModel.onResult(Result.Win(score, level))
                        if (Prefs.level == level) {
                            Prefs.level++
                        }
                    }
                    onResult()
                }
            }
        }

        // Spawning elements at regular intervals with difficulty scaling
        if (isSettingsVisible) {
            BackHandler { isSettingsVisible = false }
            OptionsScreen { isSettingsVisible = false }
        }
        else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(id = R.drawable.background),
                        contentScale = ContentScale.Crop
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Score and Level Display
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.menubutton), // Заменить на ваш ресурс
                            contentDescription = "Back Button",
                            modifier = Modifier
                                .size(80.dp)
                                .padding(16.dp)
                                .clickable {
                                    onBack()
                                },
                            contentScale = ContentScale.FillBounds
                        )
                        Column(
                            Modifier
                                .width(160.dp)
                                .height(60.dp)
                                .paint(
                                    painter = painterResource(id = R.drawable.btnbg),
                                    contentScale = ContentScale.FillBounds
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "SCORE",
                                fontFamily = nujnoefont,
                                fontSize = 14.sp
                            )
                            Text(
                                text = score.toString(),
                                fontFamily = nujnoefont,
                                fontSize = 24.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        Image(
                            painter = painterResource(id = R.drawable.icpt), // Заменить на ваш ресурс
                            contentDescription = "Back Button",
                            modifier = Modifier
                                .size(80.dp)
                                .padding(16.dp)
                                .clickable {
                                    isSettingsVisible = !isSettingsVisible
                                },
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    Text(
                        text = "LEVEL $level",
                        fontFamily = nujnoefont,
                        fontSize = 24.sp
                    )
                }

                // Game Elements
                BoxWithConstraints(
                    contentAlignment = Alignment.Center
                ) {
                    LaunchedEffect(isGameRunning, level) {
                        if (isGameRunning) {
                            while (isGameOver not true) {
                                val newElements = createRandomElements(level, maxHeight, maxWidth)
                                elements = elements + newElements

                                val spawnDelay = when (level) {
                                    1 -> 2000L
                                    2 -> 1800
                                    3 -> 1600
                                    4 -> 1400
                                    5 -> 1200L
                                    else -> 1200L
                                }
                                delay(spawnDelay + 1500) // Faster spawning on higher levels

                                elements = elements.filter { it !in newElements }
                            }
                        }
                    }

                    elements.forEach { element ->
                        ElementItem(
                            element = element,
                            onElementClick = { clickedElement ->
                                val scoreChange = if (clickedElement.isBomb) -50 else 10
                                if (clickedElement.isBomb) {
                                    explodingElements = explodingElements + clickedElement
                                }
                                score += scoreChange
                                if (score < 0) {
                                    score = 0
                                }
                                scoreEffects = scoreEffects + (scoreChange to clickedElement)
                                elements = elements.filter { it != clickedElement }
                            }
                        )
                    }
                    explodingElements.forEach { explodingElement ->
                        ExplodeEffect(explodingElement.positionX, explodingElement.positionY) {
                            explodingElements = explodingElements - explodingElement
                        }
                    }
                    scoreEffects.forEach { (scoreChange, element) ->
                        ScoreEffect(
                            scoreChange = scoreChange,
                            x = element.positionX,
                            y = element.positionY,
                            onAnimationEnd = {
                                scoreEffects = scoreEffects.filter { it != scoreChange to element }
                            }
                        )
                    }
                }

                // Timer and Start Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (isGameRunning not true) {
                        Box(
                            Modifier
                                .width(200.dp)
                                .height(80.dp)
                                .paint(
                                    painter = painterResource(id = R.drawable.startbtn),
                                    contentScale = ContentScale.FillBounds
                                )
                                .clickable {
                                    score = 0
                                    timeRemaining = 60
                                    isGameOver = false
                                    isGameRunning = true
                                },
                        )

                    }
                    else {
                        Box(
                            Modifier
                                .width(200.dp)
                                .height(80.dp)
                                .paint(
                                    painter = painterResource(id = R.drawable.btnbg),
                                    contentScale = ContentScale.FillBounds
                                )
                        ) {
                            Text(
                                text = timeRemaining.secondsToTimeString,
                                fontFamily = nujnoefont,
                                modifier = Modifier
                                    .align(Alignment.Center),
                                fontSize = 24.sp
                            )
                        }
                    }
                }

                // Level Progression (For example, progressing every 200 points)
                LaunchedEffect(score) {
                    if (score >= 100 * level) {
                        viewModel.onResult(Result.Win(score, level))
                        onResult()
                    }
                }
            }
        }
    }

    val Int.secondsToTimeString: String
        get() {
            val minutes = this / 60
            val seconds = this % 60
            return String.format("%02d:%02d", minutes, seconds)
        }

    // Data class representing each element in the game
    data class GameElement(
        val id: Int,
        val drawableId: Int, // Drawable resource ID for the element image
        val isBomb: Boolean,
        val positionX: Float,
        val positionY: Float,
    )

    @Composable
    fun ElementItem(
        element: GameElement,
        onElementClick: (GameElement) -> Unit
    ) {
        var isVisible by remember { mutableStateOf(true) }
        var isTapped by remember { mutableStateOf(false) } // Tracks if the element was tapped

        // Animate the scale of the element when tapped
        val animatedScale by animateFloatAsState(if (isTapped) 1.2f else 1.0f) // Increase size when tapped
        Box(
            modifier = Modifier
                .offset(element.positionX.dp,element.positionY.dp)
                .scale(animatedScale) // Apply the scaling animation
                .size(50.dp) // Base size of the element
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = rememberRipple(bounded = false, radius = 5.dp, color = Color.Red),
                ) {
                    if (element.isBomb not true) { // Only animate for non-bomb elements
                        isTapped = true // Trigger the scaling animation on tap
                    }
                    isVisible = false // Hide the element after it's tapped
                    onElementClick(element) // Trigger the click event
                }
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                Image(
                    painter = painterResource(id = element.drawableId),
                    contentDescription = null
                )
            }
        }
    }
    @Composable
    fun ScoreEffect(
        scoreChange: Int,
        x: Float,
        y: Float,
        onAnimationEnd: () -> Unit = {}
    ) {
        var visible by remember { mutableStateOf(true) }
        val animatedScale by animateFloatAsState(if (visible) 1.2f else 0.0f) // scale effect
        val animatedAlpha by animateFloatAsState(if (visible) 1.0f else 0.0f) // fade effect

        LaunchedEffect(Unit) {
            delay(500L) // Show the score change for 0.5 seconds
            visible = false
            delay(300L) // Wait for fade out
            onAnimationEnd() // Notify the animation is done
        }

        Box(
            modifier = Modifier
                .offset(x.dp, y.dp)
                .scale(animatedScale)
                .alpha(animatedAlpha),
            contentAlignment = Alignment.Center
        ) {
            OutlinedText(
                text = if (scoreChange > 0) "+$scoreChange" else "$scoreChange",
                fontFamily = nujnoefont,
                fontSize = 24.sp,
                outlineColor = Color(0xFF53B4F7),
                textColor = Color.White,
            )
        }
    }


    @Composable
    fun ExplodeEffect(x: Float, y: Float, onExploded: () -> Unit = {}) {
        // Simple explosion effect using scale animation
        var exploded by remember { mutableStateOf(false) }
        val explosionScale by animateFloatAsState(if (exploded) 1f else 0.5f)

        LaunchedEffect(Unit) {
            exploded = true
            delay(500L)
            exploded = false
            delay(200L)
            onExploded()
        }

        Box(
            modifier = Modifier
                .offset(x.dp, y.dp)
                .scale(explosionScale)// You can replace this with an explosion drawable resource
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_explosion), // Placeholder explosion resource
                contentDescription = "Explosion",
                modifier = Modifier.size(100.dp)
            )
        }
    }

    // Create multiple random elements with animated positioning within the safe area (middle of the screen)

    fun createRandomElements(level: Int, maxHeight: Dp, maxWidth: Dp): List<GameElement> {
        val numberOfElements = Random.nextInt(10 + level, 20 + level) // Spawn between 3 to 6 elements
        val minDistance = 100f // Minimum distance between elements to avoid overlap
        val maxAttempts = 10 // Maximum attempts to find a valid position for each element

        // Define the central spawn area where elements can spawn
        val safeAreaStartX = -maxWidth.value // Start slightly left of the center
        val safeAreaEndX = maxWidth.value // End slightly right of the center

        val safeAreaStartY = -maxHeight.value // Start slightly below the top (middle area)
        val safeAreaEndY = maxHeight.value // End slightly above the bottom (middle area)

        val newElements = mutableListOf<GameElement>()

        repeat(numberOfElements) {
            var positionX: Float
            var positionY: Float
            var validPositionFound: Boolean
            var attempts = 0

            do {
                validPositionFound = true
                positionX = Random.nextFloat() * (safeAreaEndX - safeAreaStartX) + safeAreaStartX
                positionY = Random.nextFloat() * (safeAreaEndY - safeAreaStartY) + safeAreaStartY

                // Check distance from all existing elements
                for (element in newElements) {
                    val distance =
                        calculateDistance(positionX, positionY, element.positionX, element.positionY)
                    if (distance < minDistance) {
                        validPositionFound = false
                        attempts++
                        break
                    }
                }
            } while (!validPositionFound && attempts < maxAttempts)

            if (validPositionFound) {
                val isBomb = randomBoolean(truePossibility = 0.2f)
                val id = Random.nextInt(1000)

                val drawableId = if (isBomb) {
                    R.drawable.ic_bomb // Example bomb icon resource
                }
                else {
                    when (Random.nextInt(8)) {
                        0 -> R.drawable.ic_star
                        1 -> R.drawable.ic_heart
                        2 -> R.drawable.ic_lightning
                        3 -> R.drawable.ic_coin
                        4 -> R.drawable.ic_item1
                        5 -> R.drawable.ic_item2
                        6 -> R.drawable.ic_item3
                        else -> R.drawable.ic_gem
                    }
                }

                newElements.add(
                    GameElement(
                        id = id,
                        drawableId = drawableId,
                        isBomb = isBomb,
                        positionX = positionX * if (Random.nextBoolean()) 0.5f else -0.5f,
                        positionY = positionY * if (Random.nextBoolean()) 0.5f else -0.5f
                    )
                )
            }
        }
        return newElements
    }

    fun randomBoolean(truePossibility: Float): Boolean {
        return Random.nextFloat() < truePossibility
    }

    // Helper function to calculate distance between two points
    fun calculateDistance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))
    }