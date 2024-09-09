package com.vortex.soft.sellproductsnew.data.repository.launch

import com.vortex.soft.sellproductsnew.data.repository.common.PreferencesProvider
import com.vortex.soft.sellproductsnew.data.source.local.database.room.SellProductsDataBase
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.common.type.None
import com.vortex.soft.sellproductsnew.domain.repository.LaunchRepository

class LaunchRepositoryImpl(private val prefProvider: PreferencesProvider,
                           private val sellProductsDB: SellProductsDataBase
): LaunchRepository {
    override fun clearAllData(): Either<FailureType, None> {
        sellProductsDB.clearAllTables()
        prefProvider.removeToken()
        prefProvider.removeOrderId()
        prefProvider.removeCurrentUserId()
        return Either.Right(None())
    }
}