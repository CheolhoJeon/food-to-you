package com.charlie.fty.controller;

import com.charlie.fty.service.GreetingService;
import com.charlie.fty.service.dto.GreetingDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

  private final GreetingService service;

  public GreetingController(GreetingService service) {
    this.service = service;
  }

  @GetMapping("/greeting")
  public @ResponseBody String greeting() {
    return service.greet();
  }

  @PostMapping("/greeting")
  public @ResponseBody String greeting(@RequestBody GreetingDto greetingDto) {
    return "Nice to meet you, " + greetingDto.getName();
  }

}
