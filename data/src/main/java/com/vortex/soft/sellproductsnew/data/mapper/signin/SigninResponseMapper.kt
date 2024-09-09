package com.vortex.soft.sellproductsnew.data.mapper.signin

import com.vortex.soft.sellproductsnew.data.mapper.common.JsonMapper
import com.vortex.soft.sellproductsnew.data.source.remote.dto.signin.SigninResponseJsonDto
import com.vortex.soft.sellproductsnew.domain.dto.signin.SigninResponseDto

class SigninResponseMapper : JsonMapper<SigninResponseDto, SigninResponseJsonDto> {
    override fun mapDomainToJson(type: SigninResponseDto): SigninResponseJsonDto {
        return SigninResponseJsonDto(
            type.token,
            type.refreshToken
        )
    }
    override fun mapJsonToDomain(type: SigninResponseJsonDto): SigninResponseDto {
        return SigninResponseDto(
            type.token,
            type.refreshToken
        )
    }
}