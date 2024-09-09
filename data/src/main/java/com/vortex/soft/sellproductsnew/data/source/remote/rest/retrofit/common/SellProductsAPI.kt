package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common

import com.vortex.soft.sellproductsnew.data.source.remote.dto.StatusJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.order.OrderJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.product.ProductJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.register.RegisterJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.register.RegisterResponseJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.signin.SigninJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.signin.SigninResponseJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.user.UserJsonDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface SellProductsAPI {
    @GET("api/get-products")
    fun getProducts(@Header("Authorization") token: String): Call<List<ProductJsonDto>>

    @POST("api/login")
    fun login(@Body param: SigninJsonDto): Call<SigninResponseJsonDto>

    @POST("api/register")
    fun register(@Body param: RegisterJsonDto): Call<RegisterResponseJsonDto>

    @POST("api/user")
    fun getUser(@Header("Authorization") token: String, @Body param: Int): Call<UserJsonDto>

    @POST("api/order")
    fun sendOrder(@Header("Authorization") token: String, @Body param: OrderJsonDto): Call<StatusJsonDto>

    @GET("api/get-orders")
    fun getOrders(@Header("Authorization") token: String): Call<List<OrderJsonDto>>
}