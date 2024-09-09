package com.vortex.soft.sellproductsnew.data.repository.register.source

import com.vortex.soft.sellproductsnew.data.source.remote.dto.register.RegisterJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.register.RegisterResponseJsonDto
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

interface RegisterRemote {
    suspend fun register(registerDto: RegisterJsonDto): Either<FailureType, RegisterResponseJsonDto>
}