package com.vortex.soft.sellproductsnew.data.mapper.user

import com.vortex.soft.sellproductsnew.data.mapper.common.JsonMapper
import com.vortex.soft.sellproductsnew.data.source.remote.dto.user.UserJsonDto
import com.vortex.soft.sellproductsnew.domain.dto.user.UserDto

class UserMapper : JsonMapper<UserDto, UserJsonDto> {
    override fun mapDomainToJson(type: UserDto): UserJsonDto {
        return UserJsonDto(
                type.id,
                type.name,
                type.surname,
                type.email,
                type.location,
                type.phone,
                type.imageUrl
        )
    }
    override fun mapJsonToDomain(type: UserJsonDto): UserDto {
        return UserDto(
                type.id,
                type.name,
                type.surname,
                type.email,
                type.location,
                type.phone,
                type.imageUrl
        )
    }
}