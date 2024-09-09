package com.vortex.soft.sellproductsnew.presentation.launch

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.vortex.soft.sellproductsnew.R
import com.vortex.soft.sellproductsnew.navigation.Navigation

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Surface(color = Color.White) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {
            val (spacerTop, welcomeLogo, welcomeTitle, welcomeDescription, spacer, welcomeLogInButton, welcomeEnrollButton) = createRefs()

            Spacer(modifier = Modifier
                .constrainAs(spacerTop) {
                    centerHorizontallyTo(parent)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .height(170.dp))

            Column(
                modifier = Modifier
                    .height(150.dp)
                    .width(300.dp)
                    .constrainAs(welcomeLogo) {
                        centerHorizontallyTo(parent)
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end)
                        top.linkTo(spacerTop.bottom, 35.dp)
                    }
            ) {
                Image(painterResource(R.drawable.sell_products_3), contentDescription = null)
            }

            Text(
                text = stringResource(R.string.welcome_title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .constrainAs(welcomeTitle) {
                        centerHorizontallyTo(parent)
                        start.linkTo(parent.start)
                        top.linkTo(welcomeLogo.bottom, 24.dp)
                    }
                    .padding(bottom = 16.dp)
            )

            Text(
                text = stringResource(R.string.welcome_description),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .constrainAs(welcomeDescription) {
                        centerHorizontallyTo(parent)
                        start.linkTo(parent.start)
                        top.linkTo(welcomeTitle.bottom, 10.dp)
                    }
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier
                .constrainAs(spacer) {
                    centerHorizontallyTo(parent)
                    start.linkTo(parent.start)
                    top.linkTo(welcomeDescription.bottom, 10.dp)
                }
                .height(52.dp))

            Button(
                onClick = { navController.navigate(Navigation.Login.destination) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                shape = RoundedCornerShape(35.dp),
                modifier = Modifier.constrainAs(welcomeLogInButton) {
                    centerHorizontallyTo(parent)
                    start.linkTo(parent.start, 24.dp)
                    end.linkTo(parent.end, 24.dp)
                    top.linkTo(spacer.bottom, 10.dp)
                    height = Dimension.value(60.dp)
                    width = Dimension.fillToConstraints
                }
            )
            {
                Text(
                    text = stringResource(R.string.login_header),
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge)
            }

            Button(
                onClick = { navController.navigate(Navigation.Registration.destination) },
                border = BorderStroke(1.dp, Color.Gray),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                modifier = Modifier.constrainAs(welcomeEnrollButton) {
                    centerHorizontallyTo(parent)
                    start.linkTo(parent.start, 24.dp)
                    end.linkTo(parent.end, 24.dp)
                    top.linkTo(welcomeLogInButton.bottom, 18.dp)
                    height = Dimension.value(60.dp)
                    width = Dimension.fillToConstraints
                }
            )
            {
                Text(
                    text = stringResource(R.string.enroll_header),
                    color = Color.Black,
                    style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}