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
import retrofit2.Retrofit


class NetworkService (val retrofit: Retrofit) : SellProductsAPI {
    private val api: SellProductsAPI by lazy { retrofit.create(SellProductsAPI::class.java) }

    override fun getProducts(token: String): Call<List<ProductJsonDto>> = api.getProducts(token)
    override fun login(param: SigninJsonDto): Call<SigninResponseJsonDto> = api.login(param)
    override fun register(param: RegisterJsonDto): Call<RegisterResponseJsonDto> = api.register(param)
    override fun getUser(token: String, param: Int): Call<UserJsonDto> = api.getUser(token, param)
    override fun getOrders(token: String): Call<List<OrderJsonDto>> = api.getOrders(token)
    override fun sendOrder(token: String, param: OrderJsonDto): Call<StatusJsonDto> = api.sendOrder(token, param)
}