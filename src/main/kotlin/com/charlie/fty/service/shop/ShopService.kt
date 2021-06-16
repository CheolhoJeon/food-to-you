package com.charlie.fty.service.shop

import com.charlie.fty.domain.shop.ShopRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShopService(private val shopRepository: ShopRepository) {

    @Transactional(readOnly = true)
    fun getMenuBoard(shopId: Long): MenuBoard {
        val shop = shopRepository.findById(shopId).orElseThrow(::IllegalArgumentException)
        return MenuBoard(shop)
    }
}
