package com.vortex.soft.sellproductsnew.data.mapper.signin

import com.vortex.soft.sellproductsnew.data.mapper.common.JsonMapper
import com.vortex.soft.sellproductsnew.data.source.remote.dto.signin.SigninJsonDto
import com.vortex.soft.sellproductsnew.domain.dto.signin.SigninDto

class SigninMapper : JsonMapper<SigninDto, SigninJsonDto> {
    override fun mapDomainToJson(type: SigninDto): SigninJsonDto {
        return SigninJsonDto(
            type.username,
            type.password
        )
    }
    override fun mapJsonToDomain(type: SigninJsonDto): SigninDto {
        return SigninDto(
            type.username,
            type.password
        )
    }
}