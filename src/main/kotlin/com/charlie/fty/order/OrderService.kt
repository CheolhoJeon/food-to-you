package com.charlie.fty.order

import com.charlie.fty.delivery.Delivery
import com.charlie.fty.delivery.DeliveryRepository
import com.charlie.fty.service.order.Cart
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val deliveryRepository: DeliveryRepository,
    private val orderMapper: OrderMapper
) {
    @Transactional
    fun placeOrder(cart: Cart) {
        val order = orderMapper.mapFrom(cart)
        order.place()
        orderRepository.save(order)
    }

    @Transactional
    fun payOrder(orderId: Long) {
        val order = orderRepository.findById(orderId).orElseThrow(::IllegalArgumentException)
        order.payed()

        val delivery = Delivery.started(order)
        deliveryRepository.save(delivery)
    }

    @Transactional
    fun deliverOrder(orderId: Long) {
        val order = orderRepository.findById(orderId).orElseThrow(::IllegalArgumentException)
        order.delivered()

        val delivery = deliveryRepository.findById(orderId).orElseThrow(::IllegalArgumentException)
        delivery.complete()
    }
}
