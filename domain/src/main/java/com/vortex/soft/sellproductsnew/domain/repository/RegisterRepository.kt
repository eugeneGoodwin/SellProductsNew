package com.vortex.soft.sellproductsnew.domain.repository

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.register.RegisterDto
import com.vortex.soft.sellproductsnew.domain.dto.register.RegisterResponseDto

interface RegisterRepository {
    suspend fun register(registerDto: RegisterDto): Either<FailureType, RegisterResponseDto>
}