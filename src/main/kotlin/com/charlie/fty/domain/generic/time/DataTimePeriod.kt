package com.charlie.fty.domain.generic.time

import java.time.LocalDateTime

class DataTimePeriod(
    private val from: LocalDateTime,
    private val to: LocalDateTime
) {
    companion object {
        fun between(from: LocalDateTime, to: LocalDateTime) = DataTimePeriod(from, to)
    }

    fun contains(datetime: LocalDateTime) =
        (datetime.isAfter(from) || datetime == from) &&
            (datetime.isBefore(to) || datetime == to)
}
