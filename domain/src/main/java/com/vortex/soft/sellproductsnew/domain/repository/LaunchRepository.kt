package com.vortex.soft.sellproductsnew.domain.repository

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.common.type.None

interface LaunchRepository {
    fun clearAllData(): Either<FailureType, None>
}