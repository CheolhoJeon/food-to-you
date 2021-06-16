package com.charlie.fty.configure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter

@Configuration
class RequestLoggingFilterConfig {

    @Bean
    fun loggingFilter(): CommonsRequestLoggingFilter {
        val filter = CommonsRequestLoggingFilter()

        filter.setIncludeQueryString(true)
        filter.setIncludePayload(true)
        filter.setMaxPayloadLength(10000)
        filter.setIncludeHeaders(true)
        filter.setBeforeMessagePrefix("REQUEST DATA : ")

        return filter
    }
}
