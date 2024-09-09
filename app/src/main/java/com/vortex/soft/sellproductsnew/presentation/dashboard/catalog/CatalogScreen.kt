package com.vortex.soft.sellproductsnew.presentation.dashboard.catalog

import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.vortex.soft.sellproducts.mainboard.catalog.CatalogUiState
import com.vortex.soft.sellproducts.mainboard.catalog.CatalogViewModel
import com.vortex.soft.sellproductsnew.R
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderItemDto
import com.vortex.soft.sellproductsnew.domain.dto.product.ProductDto
import com.vortex.soft.sellproductsnew.domain.entity.order.OrderItemEntity
import com.vortex.soft.sellproductsnew.presentation.login.TextFieldError
import com.vortex.soft.sellproductsnew.ui.theme.veryLightGray
import org.koin.androidx.compose.koinViewModel

@Composable
fun CatalogScreen(navController: NavHostController, viewModel: CatalogViewModel = koinViewModel()) {
    val catalogUiState = viewModel.catalogUiState.collectAsStateWithLifecycle()
    val showDialog = remember { mutableStateOf(false) }
    val selectedProduct = remember { mutableStateOf<ProductDto?>(null) }

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }

    if(showDialog.value)
        AddToCartDialog(value = selectedProduct.value, setShowDialog = {
            showDialog.value = it
        }) {
            viewModel.addToCart(it)
        }

    when(catalogUiState.value){
        is CatalogUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White)
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.page_loading),
                    Modifier
                        .padding(8.dp), textAlign = TextAlign.Center
                )

                CircularProgressIndicator(
                    strokeWidth = 4.dp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                )
            }
        }
        is CatalogUiState.Error -> {
            val error = (catalogUiState.value as? CatalogUiState.Error)?.failure?.toString() ?: ""
            TextFieldError(textError = error)
        }
        is CatalogUiState.Success -> {
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
                        horizontalArrangement = Arrangement.Start) {
                        Box(
                            modifier = Modifier
                                .width(7.dp)
                                .fillMaxHeight()
                                .background(colorResource(R.color.color_header))
                        )
                        Spacer(modifier = Modifier.width(18.dp))
                        Text(
                            text = stringResource(R.string.catalog_header),
                            style = TextStyle(
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.W900,
                                fontSize = 24.sp
                            ),
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

                val list = (catalogUiState.value as CatalogUiState.Success).response
                items(list) { product ->
                    ProductListItem(product = product, { isShow, product ->
                        showDialog.value = true
                        selectedProduct.value = product
                    })
                }
            }
        }

        else -> {}
    }
}

@Composable
fun ProductListItem(product: ProductDto, setShowDialog: (Boolean, ProductDto) -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp)
            .height(100.dp)
            .fillMaxWidth(),
        elevation = cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = veryLightGray,
            contentColor = Color.Black
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .clickable {
                    setShowDialog(true, product)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(product.imageUrl.isNotEmpty()) {
                GlideImage(
                    imageModel = { product.imageUrl },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Inside,
                        alignment = Alignment.Center
                    ),
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth(0.3f)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Column(
                Modifier
                    .height(77.dp)
                    .fillMaxWidth(0.5f)
                    .padding(
                        end = 2.dp,
                    )) {
                Text(text = product.title, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = product.description, style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.width(100.dp)
            ) {
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun AddToCartDialog(value: ProductDto?, setShowDialog: (Boolean) -> Unit, setValue: (orderItemDto: OrderItemDto) -> Unit)
{
    val orderItem = remember { mutableStateOf(
        OrderItemEntity(value?.id?.toInt() ?: 0,
        value?.description ?: "",
        value?.imageUrl ?: "",
        0,
        value?.price ?: "")
    ) }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        (LocalView.current.parent as DialogWindowProvider).window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.LightGray
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(7.dp)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_cancel),
                            contentDescription = "",
                            modifier = Modifier.height(30.dp)
                                .width(30.dp).clickable { setShowDialog(false) }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    if(value?.imageUrl?.isNotEmpty() ?: false)
                        GlideImage(
                            imageModel = { value!!.imageUrl },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Inside,
                                alignment = Alignment.Center
                            ),
                            modifier = Modifier
                                .height(70.dp)
                                .width(70.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    else
                        Image(
                        painter = painterResource(R.drawable.ic_empty),
                        contentDescription = "",
                            modifier = Modifier
                                .height(70.dp)
                                .width(70.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                        text = value?.title ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                        text = value?.description ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Price: ",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.width(18.dp))
                        Text(
                            text = value?.price ?: "",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Counter{ orderItem.value = orderItem.value.copy(quantity = it) }

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.cart_total_price),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = orderItem.value.totalPrice,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                setValue(orderItem.value.toDTO())
                                setShowDialog(false)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.add_to_cart_button),
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Counter(startCount: Int = 0, updateCounter: (Int) -> Unit) {
    val count = remember{ mutableStateOf(startCount) }
    Row( modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(R.drawable.ic_minus_2),
            contentDescription = null,
            modifier = Modifier.height(18.dp).width(18.dp).clickable {
                if(count.value > 0) {
                    count.value--
                    updateCounter(count.value)
                }
            }
        )

        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = "${count.value}",
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(align = Alignment.CenterVertically)
                .align(Alignment.CenterVertically))
        Spacer(modifier = Modifier.width(14.dp))
        Image(
            painter = painterResource(R.drawable.ic_plus_2),
            contentDescription = null,
            modifier = Modifier.height(18.dp).width(18.dp).clickable {
                count.value++; updateCounter(count.value)
            }
        )
    }
}