package com.vortex.soft.sellproductsnew.presentation.dashboard.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.vortex.soft.sellproductsnew.R
import com.vortex.soft.sellproductsnew.domain.dto.user.UserDto
import com.vortex.soft.sellproductsnew.presentation.login.TextFieldError
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = koinViewModel()) {
    val profileUiState = viewModel.profileUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getUser(1)
    }

    when(profileUiState.value){
        is ProfileUiState.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.page_loading),
                    modifier = Modifier
                        .padding(8.dp), textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium
                )

                CircularProgressIndicator(
                    strokeWidth = 4.dp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                )
            }
        }
        is ProfileUiState.Error -> {
            val error = (profileUiState.value as? ProfileUiState.Error)?.failure?.toString() ?: ""
            TextFieldError(textError = error)
        }
        is ProfileUiState.Success -> {
            val user = (profileUiState.value as ProfileUiState.Success).response
            Timber.d("ProfileScreen success user ${user}")
            ProfileContent(user)
        }
    }
}

@Composable
fun ProfileContent(user: UserDto) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(
                end = 2.dp,
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(14.dp))
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "avatar",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(164.dp)
                .clip(CircleShape)                       // clip to the circle shape
                .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
        )
        Spacer(modifier = Modifier.height(34.dp))
        Text(text = user.name, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(14.dp))
        Text(text = user.surname, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(34.dp))
        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .padding(start = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.icon_email),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(25.dp))
            Text(text = user.email, style = MaterialTheme.typography.bodySmall)
        }
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .padding(start = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.icon_location),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(25.dp))
            Text(text = user.location, style = MaterialTheme.typography.bodySmall)
        }
    }
}