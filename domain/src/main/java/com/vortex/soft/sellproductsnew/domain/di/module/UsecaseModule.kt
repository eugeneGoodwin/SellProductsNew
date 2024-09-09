package com.vortex.soft.sellproductsnew.domain.di.module

import com.vortex.soft.sellproductsnew.domain.interactor.usecases.cart.AddOrderItemToCartUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.cart.CreateCartUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.cart.GetCartUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.cart.IsCartExistUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.launch.ClearAllDataUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.order.GetOrdersUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.order.SendOrderUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.product.GetProductsUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.register.RegisterUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.signin.SigninUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.user.GetCurrentUserIdUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.user.GetUserUseCase
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.user.SetCurrentUserIdUseCase
import org.koin.dsl.module

val usecaseModule = module {
    factory { SigninUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { GetProductsUseCase(get()) }
    factory { GetUserUseCase(get()) }
    factory { GetOrdersUseCase(get()) }
    factory { SendOrderUseCase(get()) }

    factory { CreateCartUseCase(get()) }
    factory { GetCartUseCase(get()) }
    factory { IsCartExistUseCase(get()) }
    factory { AddOrderItemToCartUseCase(get()) }

    factory { GetCurrentUserIdUseCase(get()) }
    factory { SetCurrentUserIdUseCase(get()) }

    factory { ClearAllDataUseCase(get()) }
}