package com.vortex.soft.sellproductsnew.data.repository.register

import com.vortex.soft.sellproductsnew.data.mapper.register.RegisterMapper
import com.vortex.soft.sellproductsnew.data.mapper.register.RegisterResponseMapper
import com.vortex.soft.sellproductsnew.data.repository.register.source.RegisterRemote
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.monad.map
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.register.RegisterDto
import com.vortex.soft.sellproductsnew.domain.dto.register.RegisterResponseDto
import com.vortex.soft.sellproductsnew.domain.repository.RegisterRepository

class RegisterRepositoryImpl(
    private val remote: RegisterRemote,
    private val mapper: RegisterMapper,
    private val responseMapper: RegisterResponseMapper
): RegisterRepository {

    override suspend fun register(registerDto: RegisterDto): Either<FailureType, RegisterResponseDto> {
        return remote.register(mapper.mapDomainToJson(registerDto)).map { responseMapper.mapJsonToDomain(it) }
    }
}