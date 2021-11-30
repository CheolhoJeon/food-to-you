package com.charlie.fty

import com.charlie.fty.generic.money.Money
import com.charlie.fty.service.order.Cart
import com.charlie.fty.service.order.CartLineItem
import com.charlie.fty.service.order.CartOption
import com.charlie.fty.service.order.CartOptionGroup
import com.charlie.fty.order.OrderService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application(private val orderService: OrderService) : CommandLineRunner {

    fun main(args: Array<String>) {
        runApplication<Application>(*args)
    }

    override fun run(vararg args: String?) {
        val cart = Cart(
            1L, 1L,
            CartLineItem(
                1L, "삼겹살 1인세트", 2,
                CartOptionGroup(
                    "기본",
                    CartOption("소(250g)", Money.wons(12000))
                )
            )
        )

        orderService.placeOrder(cart)
        orderService.payOrder(1L)
        orderService.deliverOrder(1L)
    }
}
