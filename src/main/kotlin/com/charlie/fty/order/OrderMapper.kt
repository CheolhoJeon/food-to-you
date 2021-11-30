package com.charlie.fty.order

import com.charlie.fty.service.order.Cart
import com.charlie.fty.service.order.CartLineItem
import com.charlie.fty.service.order.CartOption
import com.charlie.fty.service.order.CartOptionGroup
import com.charlie.fty.shop.MenuRepository
import com.charlie.fty.shop.ShopRepository
import org.springframework.stereotype.Component

@Component
class OrderMapper(
    private val menuRepository: MenuRepository,
    private val shopRepository: ShopRepository
) {
    fun mapFrom(cart: Cart): Order {
        val shop = shopRepository.findById(cart.shopId)
            .orElseThrow(::IllegalArgumentException)

        return Order(
            cart.userId,
            shop,
            cart.cartLineItems
                .map(::toOrderLineItem)
        )
    }

    private fun toOrderLineItem(cartLineItem: CartLineItem): OrderLineItem {
        val menu = menuRepository.findById(cartLineItem.menuId)
            .orElseThrow(::IllegalArgumentException)

        return OrderLineItem(
            menu,
            cartLineItem.name,
            cartLineItem.count,
            cartLineItem.groups.map(::toOrderOptionGroup)
        )
    }

    private fun toOrderOptionGroup(cartOptionGroup: CartOptionGroup) =
        OrderOptionGroup(
            cartOptionGroup.name,
            cartOptionGroup.options
                .map(::toOderOption)
        )

    private fun toOderOption(cartOption: CartOption) =
        OrderOption(cartOption.name, cartOption.price)
}
