package com.charlie.fty.controller

import com.charlie.fty.service.GreetingService
import com.charlie.fty.service.dto.GreetingDto
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/greeting")
class GreetingController(private val service: GreetingService) {

    @GetMapping
    @ResponseBody
    fun greeting(): String = service.greet()

    @PostMapping
    @ResponseBody
    fun greeting(@RequestBody greetingDto: GreetingDto): String = "Nice to meet you, ${greetingDto.name}"
}
