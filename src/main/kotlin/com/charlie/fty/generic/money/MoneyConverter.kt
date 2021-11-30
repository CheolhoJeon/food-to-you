package com.charlie.fty.generic.money

import com.charlie.fty.generic.money.Money
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class MoneyConverter : AttributeConverter<Money, Long> {
    override fun convertToDatabaseColumn(money: Money) =
        money.amount.longValueExact()

    override fun convertToEntityAttribute(amount: Long) =
        Money.wons(amount)
}
