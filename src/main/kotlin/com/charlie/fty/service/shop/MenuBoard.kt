package com.charlie.fty.service.shop

import com.charlie.fty.domain.generic.money.Money
import com.charlie.fty.domain.shop.Menu
import com.charlie.fty.domain.shop.Shop

data class MenuBoard(
    val shopId: Long,
    val shopName: String,
    val open: Boolean,
    val minOrderAmount: Money,
    val menuItems: List<MenuItem>
) {
    constructor(shop: Shop) : this(
        shop.getId(),
        shop.getName(),
        shop.isOpen,
        shop.getMinOrderAmount(),
        shop.menus.map(::MenuItem)
    )
}

data class MenuItem(
    val menuId: Long,
    val menuName: String,
    val menuBasePrice: Money,
    val menuDescription: String
) {
    constructor(menu: Menu) : this(
        menu.getId(),
        menu.getName(),
        menu.getBasePrice(),
        menu.getDescription()
    )
}
