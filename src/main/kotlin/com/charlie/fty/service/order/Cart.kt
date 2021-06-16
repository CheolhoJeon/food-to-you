package com.charlie.fty.service.order

import com.charlie.fty.domain.generic.money.Money

data class Cart(
    val shopId: Long = 0L,
    val userId: Long = 0L,
    val cartLineItems: List<CartLineItem> = listOf()
)

data class CartLineItem(
    val menuId: Long = 0L,
    val name: String = "unknown",
    val count: Int = 0,
    val groups: List<CartOptionGroup> = listOf()
)

data class CartOptionGroup(
    val name: String = "unknown",
    val options: List<CartOption> = listOf()
)

data class CartOption(
    val name: String = "unknown",
    val price: Money = Money.ZERO
)
