package ir.one_developer.batmanmovies.ui.screen.splash

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.isTraceInProgress
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.one_developer.batmanmovies.R
import ir.one_developer.batmanmovies.ui.theme.BatmanMoviesTheme

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

@Composable
fun SplashScreen(
    onNavigate: () -> Unit
) = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
) {

    var isLoading by rememberSaveable { mutableStateOf(true) }

    val logoTransition by animateFloatAsState(
        label = "logo transition",
        targetValue = if (isLoading) 0F else 1F,
        animationSpec = tween(durationMillis = 1200)
    )

    Image(
        contentDescription = null,
        modifier = Modifier
            .size(150.dp)
            .scale(logoTransition),
        painter = painterResource(R.drawable.logo)
    )

    LaunchedEffect(key1 = logoTransition) {
        if (logoTransition >= 1F) {
            onNavigate()
            return@LaunchedEffect
        }
        if (logoTransition < 1F) {
            isLoading = false
        }
    }

}

@Composable
@Preview(name = "Light", showBackground = true)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
private fun SplashScreenPreview() = BatmanMoviesTheme {
    SplashScreen(onNavigate = {})
}