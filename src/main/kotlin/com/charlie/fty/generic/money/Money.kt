package com.charlie.fty.generic.money

import java.math.BigDecimal
import java.util.Objects
import java.util.function.Function

class Money constructor(val amount: BigDecimal) {

    companion object {
        val ZERO = wons(0);
        fun wons(amount: Long) = Money(BigDecimal.valueOf(amount))
        fun wons(amount: Double) = Money(BigDecimal.valueOf(amount))
        fun <T> sum(bags: Collection<T>, monetary: Function<T, Money>) =
            bags
                // 코틀린 함수로 수정 필요:
                .stream()
                .map { bag -> monetary.apply(bag) }
                .reduce(ZERO, Money::plus)
    }

    fun plus(amount: Money) = Money(this.amount.add(amount.amount))

    fun minus(amount: Money) = Money(this.amount.subtract(amount.amount))

    fun times(percent: Double) = Money(this.amount.multiply(BigDecimal.valueOf(percent)))

    fun divide(divisor: Double) = Money(amount.divide(BigDecimal.valueOf(divisor)))

    fun isLessThan(other: Money) = amount.compareTo(other.amount) < 0

    fun isGreaterThanOrEqual(other: Money) = amount.compareTo(other.amount) >= 0

    fun longValue() = amount.toLong()

    fun doubleValue() = amount.toDouble()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is Money) {
            return false
        }
        return amount.toDouble() == other.amount.toDouble()
    }

    override fun hashCode() = Objects.hashCode(amount)

    override fun toString() = amount.toString() + "원"
}
