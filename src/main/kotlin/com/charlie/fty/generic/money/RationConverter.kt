package com.charlie.fty.generic.money

import com.charlie.fty.generic.money.Ratio
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class RationConverter : AttributeConverter<Ratio, Double> {

    override fun convertToDatabaseColumn(ratio: Ratio) = ratio.rate
    override fun convertToEntityAttribute(rate: Double) = Ratio.valueOf(rate)
}
