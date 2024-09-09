package com.vortex.soft.sellproductsnew.presentation.dashboard.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.vortex.soft.sellproducts.mainboard.cart.CartUiState
import com.vortex.soft.sellproducts.mainboard.cart.CartViewModel
import com.vortex.soft.sellproductsnew.R
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto
import com.vortex.soft.sellproductsnew.domain.entity.order.OrderEntity
import com.vortex.soft.sellproductsnew.domain.entity.order.OrderItemEntity
import com.vortex.soft.sellproductsnew.presentation.dashboard.catalog.Counter
import com.vortex.soft.sellproductsnew.presentation.login.TextFieldError
import com.vortex.soft.sellproductsnew.ui.theme.veryLightGray
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartScreen(navController: NavHostController, viewModel: CartViewModel = koinViewModel()) {
    val cartUiState = viewModel.cartUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.isCartExist()
    }

    when(cartUiState.value){
        is CartUiState.Loading -> {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.page_loading),
                    modifier = Modifier
                        .padding(8.dp), textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayMedium
                )

                CircularProgressIndicator(
                    strokeWidth = 4.dp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                )
            }
        }
        is CartUiState.Empty -> {
            Text(
                text = "Empty Cart",
                Modifier.padding(8.dp), textAlign = TextAlign.Center
            )
        }
        is CartUiState.Error -> {
            val error = (cartUiState.value as? CartUiState.Error)?.failure?.toString() ?: ""
            TextFieldError(textError = error)
        }
        is CartUiState.Success -> {
            val order = (cartUiState.value as CartUiState.Success).response
            OrderContent(order, viewModel)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OrderContent(order: OrderDto, viewModel: CartViewModel) {

    val orderEntity = remember { mutableStateOf(OrderEntity(order)) }

    Scaffold {
        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .padding(10.dp),
            contentPadding = PaddingValues(bottom = 70.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .width(7.dp)
                            .fillMaxHeight()
                            .background(colorResource(R.color.color_header))
                    )
                    Spacer(modifier = Modifier.width(18.dp))
                    Text(
                        text = stringResource(R.string.cart_header),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .align(Alignment.CenterVertically)
                    )

                }
            }

            item {
                Spacer(modifier = Modifier.height(22.dp))
            }

            itemsIndexed(orderEntity.value.listOrderItems) { index, item ->
                CartOrderItem(index = index, orderItem = item) { idx, count ->
                    val items = orderEntity.value.listOrderItems
                    val orderItem = items[idx]
                    orderItem.quantity = count
                    items.set(idx, orderItem)
                    orderEntity.value = orderEntity.value.copy(items = items)
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .height(110.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .padding(2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .height(70.dp)
                                .fillMaxWidth()
                                .padding(start = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Total price:",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.width(25.dp))
                            Text(
                                text = orderEntity.value.totalPrice,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Row(
                            modifier = Modifier
                                .height(70.dp)
                                .fillMaxWidth()
                                .padding(start = 8.dp, bottom = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Status:",
                                style = MaterialTheme.typography.bodyMedium
                                )
                            Spacer(modifier = Modifier.width(25.dp))
                            Text(
                                text = orderEntity.value.status,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(25.dp))
            }

            item {
                Button(
                    onClick = { viewModel.checkout(orderEntity.value.toDTO()) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    shape = RoundedCornerShape(35.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(text = stringResource(R.string.cart_button))
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun CartOrderItem(index: Int, orderItem: OrderItemEntity, callback: (index: Int, count: Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .height(110.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = veryLightGray,
            contentColor = Color.Black
        )
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (orderItem.productImageUrl.isNotEmpty())
                GlideImage(
                    imageModel = { orderItem.productImageUrl },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Inside,
                        alignment = Alignment.Center
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .padding(start = 5.dp)
                )
            else
                Image(
                    painter = painterResource(R.drawable.ic_empty),
                    contentDescription = "",
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .padding(start = 5.dp)
                )

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                Modifier
                    .wrapContentHeight()
                    .width(170.dp)
                    .padding(7.dp)
            ) {
                Text(
                    text = "Id: " + orderItem.productId.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(7.dp))
                Text(text = orderItem.productDesciption, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    text = "Total price:  " + orderItem.totalPrice,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(7.dp))
            }

            Spacer(modifier = Modifier.width(14.dp))

            Counter(updateCounter = { callback(index, it) }, startCount = orderItem.quantity)
        }
    }
}