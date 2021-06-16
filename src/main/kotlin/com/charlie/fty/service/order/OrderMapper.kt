package com.charlie.fty.service.order

import com.charlie.fty.domain.order.Order
import com.charlie.fty.domain.order.OrderLineItem
import com.charlie.fty.domain.order.OrderOption
import com.charlie.fty.domain.order.OrderOptionGroup
import com.charlie.fty.domain.shop.MenuRepository
import com.charlie.fty.domain.shop.ShopRepository
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
