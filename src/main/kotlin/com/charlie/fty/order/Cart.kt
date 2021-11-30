package com.charlie.fty.service.order

import com.charlie.fty.generic.money.Money

data class Cart(
    val shopId: Long = 0L,
    val userId: Long = 0L,
    val cartLineItems: List<CartLineItem> = listOf()
) {
    constructor(shopId: Long, userId: Long, vararg cartLineItems: CartLineItem) : this(
        shopId, userId, cartLineItems.toList()
    )
}

data class CartLineItem(
    val menuId: Long = 0L,
    val name: String = "unknown",
    val count: Int = 0,
    val groups: List<CartOptionGroup> = listOf()
) {
    constructor(menuId: Long, name: String, count: Int, vararg groups: CartOptionGroup) : this(
        menuId, name, count, groups.toList()
    )
}

data class CartOptionGroup(
    val name: String = "unknown",
    val options: List<CartOption> = listOf()
) {
    constructor(name: String, vararg options: CartOption) : this(
        name, options.toList()
    )
}

data class CartOption(
    val name: String = "unknown",
    val price: Money = Money.ZERO
)
