package com.vortex.soft.sellproductsnew.presentation.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vortex.soft.sellproductsnew.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(5000)
        onNavigate()
    }

    Box(modifier = Modifier.fillMaxSize().padding(start = 15.dp, end = 15.dp), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(R.drawable.sell_products_3),
            contentDescription = ""
        )
    }
}