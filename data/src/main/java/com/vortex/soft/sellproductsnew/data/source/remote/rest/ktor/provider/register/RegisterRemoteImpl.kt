package com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.provider.register

import com.vortex.soft.sellproductsnew.data.repository.register.source.RegisterRemote
import com.vortex.soft.sellproductsnew.data.source.remote.dto.register.RegisterJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.register.RegisterResponseJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter.RestCallAdapter
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter.api_register
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

class RegisterRemoteImpl (
    private val restAdapter: RestCallAdapter
) : RegisterRemote {

    override suspend fun register(registerDto: RegisterJsonDto): Either<FailureType, RegisterResponseJsonDto> {
        return restAdapter.make<RegisterResponseJsonDto, RegisterResponseJsonDto>(
            { api_register(registerDto) }, { it })
    }
}