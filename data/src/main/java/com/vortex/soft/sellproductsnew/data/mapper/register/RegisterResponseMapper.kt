package com.vortex.soft.sellproductsnew.data.mapper.register

import com.vortex.soft.sellproductsnew.data.mapper.common.JsonMapper
import com.vortex.soft.sellproductsnew.data.source.remote.dto.register.RegisterResponseJsonDto
import com.vortex.soft.sellproductsnew.domain.dto.register.RegisterResponseDto

class RegisterResponseMapper : JsonMapper<RegisterResponseDto, RegisterResponseJsonDto> {
    override fun mapDomainToJson(type: RegisterResponseDto): RegisterResponseJsonDto {
        return RegisterResponseJsonDto(
            type.token,
            type.refreshToken
        )
    }
    override fun mapJsonToDomain(type: RegisterResponseJsonDto): RegisterResponseDto {
        return RegisterResponseDto(
            type.token,
            type.refreshToken
        )
    }
}