package ir.one_developer.batmanmovies.utils.ext

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

@Composable
fun Modifier.loading(
    visible: Boolean,
    color: Color = Color.LightGray,
    shape: Shape = MaterialTheme.shapes.medium,
    highlight: PlaceholderHighlight = PlaceholderHighlight.shimmer()
) = placeholder(
    shape = shape,
    color = color,
    visible = visible,
    highlight = highlight
)