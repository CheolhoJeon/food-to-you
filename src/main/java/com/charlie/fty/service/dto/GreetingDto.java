package com.charlie.fty.service.dto;

public class GreetingDto {

  private String name;

  public GreetingDto() {
  }

  public GreetingDto(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

}
