package com.charlie.fty.domain.shop;

import com.charlie.fty.domain.generic.money.Money;
import lombok.Builder;
import lombok.Data;

@Data
public class Option {
    private String name;
    private Money price;

    @Builder
    public Option(String name, Money price) {
        this.name = name;
        this.price = price;
    }
}
