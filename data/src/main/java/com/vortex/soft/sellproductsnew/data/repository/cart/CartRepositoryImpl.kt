package com.vortex.soft.sellproductsnew.data.repository.cart

import com.vortex.soft.sellproductsnew.data.mapper.order.ComplexOrderSqlMapper
import com.vortex.soft.sellproductsnew.data.mapper.order.OrderItemSqlMapper
import com.vortex.soft.sellproductsnew.data.repository.common.PreferencesProvider
import com.vortex.soft.sellproductsnew.data.repository.order.source.OrderItemLocal
import com.vortex.soft.sellproductsnew.data.repository.order.source.OrderLocal
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderDto
import com.vortex.soft.sellproductsnew.domain.dto.order.OrderItemDto
import com.vortex.soft.sellproductsnew.domain.entity.order.OrderEntity
import com.vortex.soft.sellproductsnew.domain.repository.CartRepository

class CartRepositoryImpl (
    private val orderLocal: OrderLocal,
    private val orderItemLocal: OrderItemLocal,
    private val prefProvider: PreferencesProvider,
    private val complexMapper: ComplexOrderSqlMapper,
    private val orderItemSqlMapper: OrderItemSqlMapper
): CartRepository {

    override fun createCart(order: OrderDto): Either<FailureType, Boolean> {
        prefProvider.saveOrderId(order.id)
        val(orderEntity, listOrderItemEntity) = complexMapper.mapDomainToSql(order)
        orderLocal.insert(orderEntity)
        listOrderItemEntity.forEach { orderItemLocal.insert(it) }
        return Either.Right(true)
    }

    override fun isCartExist(): Either<FailureType, Boolean> {
        return if(prefProvider.getOrderId().isNotEmpty()) Either.Right(true) else Either.Right(false)
    }

    override fun addOrderItemToCart(orderItem: OrderItemDto): Either<FailureType, Boolean> {
        val orderId = prefProvider.getOrderId()
        if(orderId.isNotEmpty()){
            orderItemLocal.insert(orderItemSqlMapper.mapDomainToSql(orderId, orderItem))
            val orderEntity = orderLocal.getByOrderId(orderId)
            val listOrderItemEntity = orderItemLocal.getByOrderId(orderId)
            val cart = complexMapper.mapSqlToDomain(orderEntity, listOrderItemEntity)
            val entity = OrderEntity(cart)
            val(orderEntityMod, listOrderItemEntityMod) = complexMapper.mapDomainToSql(entity.toDTO())
            //orderLocal.update(orderEntityMod)
            orderLocal.update(orderId, orderEntityMod.totalPrice)
            return Either.Right(true)
        }
        return Either.Left(FailureType.CartEmptyError)
    }

    override fun getCart(): Either<FailureType, OrderDto> {
        val orderId = prefProvider.getOrderId()
        if(orderId.isNotEmpty()){
            val orderEntity = orderLocal.getByOrderId(orderId)
            val listOrderItemEntity = orderItemLocal.getByOrderId(orderId)
            val cart = complexMapper.mapSqlToDomain(orderEntity, listOrderItemEntity)
            return Either.Right(cart)
        }
        return Either.Left(FailureType.CartEmptyError)
    }
}