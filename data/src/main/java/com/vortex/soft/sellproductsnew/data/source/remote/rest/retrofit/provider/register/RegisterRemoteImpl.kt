package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.provider.register

import com.vortex.soft.sellproductsnew.data.repository.register.source.RegisterRemote
import com.vortex.soft.sellproductsnew.data.source.remote.dto.register.RegisterJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.register.RegisterResponseJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.RestAdapter
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.SellProductsAPI
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

class RegisterRemoteImpl (
    private val apiService: SellProductsAPI,
    private val restAdapter: RestAdapter
) : RegisterRemote {

    override suspend fun register(registerDto: RegisterJsonDto): Either<FailureType, RegisterResponseJsonDto> {
        return restAdapter.make(apiService.register(registerDto), { it })
    }
}