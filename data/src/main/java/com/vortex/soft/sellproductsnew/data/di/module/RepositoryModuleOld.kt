package com.vortex.soft.sellproductsnew.data.di.module

import android.content.Context
import com.vortex.soft.sellproductsnew.data.mapper.order.ComplexOrderSqlMapper
import com.vortex.soft.sellproductsnew.data.mapper.order.OrderItemMapper
import com.vortex.soft.sellproductsnew.data.mapper.order.OrderItemSqlMapper
import com.vortex.soft.sellproductsnew.data.mapper.order.OrderMapper
import com.vortex.soft.sellproductsnew.data.mapper.product.ProductMapper
import com.vortex.soft.sellproductsnew.data.mapper.register.RegisterMapper
import com.vortex.soft.sellproductsnew.data.mapper.register.RegisterResponseMapper
import com.vortex.soft.sellproductsnew.data.mapper.signin.SigninMapper
import com.vortex.soft.sellproductsnew.data.mapper.signin.SigninResponseMapper
import com.vortex.soft.sellproductsnew.data.mapper.user.UserMapper
import com.vortex.soft.sellproductsnew.data.repository.cart.CartRepositoryImpl
import com.vortex.soft.sellproductsnew.data.repository.launch.LaunchRepositoryImpl
import com.vortex.soft.sellproductsnew.data.repository.order.OrderRepositoryImpl
import com.vortex.soft.sellproductsnew.data.repository.order.source.OrderRemote
import com.vortex.soft.sellproductsnew.data.repository.product.ProductRepositoryImpl
import com.vortex.soft.sellproductsnew.data.repository.product.source.ProductRemote
import com.vortex.soft.sellproductsnew.data.repository.register.RegisterRepositoryImpl
import com.vortex.soft.sellproductsnew.data.repository.register.source.RegisterRemote
import com.vortex.soft.sellproductsnew.data.repository.signin.SigninRepositoryImpl
import com.vortex.soft.sellproductsnew.data.repository.signin.source.SigninRemote
import com.vortex.soft.sellproductsnew.data.repository.user.UserRepositoryImpl
import com.vortex.soft.sellproductsnew.data.repository.user.source.UserRemote
import com.vortex.soft.sellproductsnew.data.source.preferences.PreferencesProviderImpl
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.NetworkHandler
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.RestAdapter
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.provider.order.OrderRemoteImpl
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.provider.product.ProductRemoteImpl
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.provider.register.RegisterRemoteImpl
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.provider.signin.SigninRemoteImpl
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.provider.user.UserRemoteImpl
import com.vortex.soft.sellproductsnew.domain.repository.*
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModuleOld = module {
    factory { NetworkHandler(androidApplication()) }

    single { androidApplication().getSharedPreferences(androidContext().packageName, Context.MODE_PRIVATE) }

    factory <SigninRemote> { SigninRemoteImpl(get(), restAdapter = RestAdapter(get())) }
    factory <ProductRemote> { ProductRemoteImpl(get(), restAdapter = RestAdapter(get())) }
    factory <RegisterRemote> { RegisterRemoteImpl(get(), restAdapter = RestAdapter(get())) }
    factory <UserRemote> { UserRemoteImpl(get(), restAdapter = RestAdapter(get())) }
    factory <OrderRemote> { OrderRemoteImpl(get(), restAdapter = RestAdapter(get())) }

    factory <SigninRepository> { SigninRepositoryImpl(get(), mapper = SigninMapper(), responseMapper = SigninResponseMapper()) }
    factory <ProductRepository> { ProductRepositoryImpl(get(), mapper = ProductMapper(), prefProvider = PreferencesProviderImpl(get())) }
    factory <RegisterRepository> { RegisterRepositoryImpl(get(), mapper = RegisterMapper(), responseMapper = RegisterResponseMapper()) }
    factory <UserRepository> { UserRepositoryImpl(get(), mapper = UserMapper(), prefProvider = PreferencesProviderImpl(get())) }
    factory <OrderRepository> { OrderRepositoryImpl(get(), mapper = OrderMapper(OrderItemMapper()), prefProvider = PreferencesProviderImpl(get())) }
    factory <CartRepository> { CartRepositoryImpl(get(), get(), prefProvider = PreferencesProviderImpl(get()), complexMapper = ComplexOrderSqlMapper(), orderItemSqlMapper = OrderItemSqlMapper()) }

    factory <LaunchRepository> { LaunchRepositoryImpl(prefProvider = PreferencesProviderImpl(get()), get()) }
}