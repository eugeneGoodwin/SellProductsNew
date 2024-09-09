package com.vortex.soft.sellproductsnew.presentation.dashboard.orders

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.vortex.soft.sellproductsnew.R
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderItemDto
import com.vortex.soft.sellproductsnew.presentation.login.TextFieldError
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(navController: NavHostController, viewModel: OrdersViewModel = koinViewModel()) {
    val ordersUiState = viewModel.ordersUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getOrders()
    }

    when(ordersUiState.value){
        is OrdersUiState.Loading -> {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(12.dp)
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
        is OrdersUiState.Error -> {
            val error = (ordersUiState.value as? OrdersUiState.Error)?.failure?.toString() ?: ""
            TextFieldError(textError = error)
        }
        is OrdersUiState.Success -> {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 80.dp)
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
                            text = stringResource(R.string.orders_header),
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

                val list = (ordersUiState.value as OrdersUiState.Success).response
                items(list) {
                    OrderListItem(order = it)
                }
            }
        }
    }
}

@Composable
fun OrderListItem(order: OrderDto) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Column(
            Modifier
                .wrapContentHeight()
                .padding(8.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = order.id, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.width(25.dp))
                Text(text = order.orderDate, style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Status:", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.width(14.dp))
                Text(text = order.status, style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(10.dp)
            ) {
                val list = order.listOrderItems
                list.forEach { orderItem ->
                    OrderItem(orderItem = orderItem)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Total Price:", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.width(14.dp))
                Text(text = order.totalPrice, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun OrderItem(orderItem: OrderItemDto) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = orderItem.productDescription, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.width(15.dp))
        Text(text = orderItem.quantity.toString(), style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.width(15.dp))
        Text(text = orderItem.totalPrice, style = MaterialTheme.typography.bodySmall)
    }
}