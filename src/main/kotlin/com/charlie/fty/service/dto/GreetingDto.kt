package com.charlie.fty.service.dto

// data class GreetingDto(var name: String)
class GreetingDto() {

    lateinit var name: String

    constructor(name: String) : this() {
        this.name = name
    }
}
