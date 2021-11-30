package com.charlie.fty.generic.money

class Ratio private constructor(val rate: Double) {

    companion object {
        fun valueOf(rate: Double) = Ratio(rate)
    }

    fun of(price: Money) = price.times(rate)
}
