package com.vortex.soft.sellproductsnew.data.repository.signin

import com.vortex.soft.sellproductsnew.data.mapper.signin.SigninMapper
import com.vortex.soft.sellproductsnew.data.mapper.signin.SigninResponseMapper
import com.vortex.soft.sellproductsnew.data.repository.signin.source.SigninRemote
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.monad.map
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.signin.SigninDto
import com.vortex.soft.sellproductsnew.domain.dto.signin.SigninResponseDto
import com.vortex.soft.sellproductsnew.domain.repository.SigninRepository

class SigninRepositoryImpl(
    private val remote: SigninRemote,
    private val mapper: SigninMapper,
    private val responseMapper: SigninResponseMapper
) : SigninRepository {

    override suspend fun login(signinDto: SigninDto): Either<FailureType, SigninResponseDto> {
        return remote.login( mapper.mapDomainToJson(signinDto)).map {
            responseMapper.mapJsonToDomain(it)
        }
    }
}