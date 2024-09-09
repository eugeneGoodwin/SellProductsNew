package com.vortex.soft.sellproductsnew.data.mapper.register

import com.vortex.soft.sellproductsnew.data.mapper.common.JsonMapper
import com.vortex.soft.sellproductsnew.data.source.remote.dto.register.RegisterJsonDto
import com.vortex.soft.sellproductsnew.domain.dto.register.RegisterDto

class RegisterMapper : JsonMapper<RegisterDto, RegisterJsonDto> {
    override fun mapDomainToJson(type: RegisterDto): RegisterJsonDto {
        return RegisterJsonDto(
            type.username,
            type.email,
            type.password,
            type.passwordConfirm
        )
    }
    override fun mapJsonToDomain(type: RegisterJsonDto): RegisterDto {
        return RegisterDto(
            type.username,
            type.email,
            type.password,
            type.passwordConfirm
        )
    }
}