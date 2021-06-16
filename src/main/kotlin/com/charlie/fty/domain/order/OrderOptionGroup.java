package com.charlie.fty.domain.order;

import com.charlie.fty.domain.generic.money.Money;
import com.charlie.fty.domain.shop.OptionGroup;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "ORDER_OPTION_GROUPS")
@Getter
public class OrderOptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_OPTION_GROUP_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ElementCollection
    @CollectionTable(name = "ORDER_OPTIONS", joinColumns = @JoinColumn(name = "ORDER_OPTION_GROUP_ID"))
    private List<OrderOption> orderOptions;

    public OrderOptionGroup(String name, List<OrderOption> options) {
        this(null, name, options);
    }

    @Builder
    public OrderOptionGroup(Long id, String name, List<OrderOption> options) {
        this.id = id;
        this.name = name;
        this.orderOptions = options;
    }

    OrderOptionGroup() {
    }

    public Money calculatePrice() {
        return Money.Companion.sum(orderOptions, OrderOption::getPrice);
    }

    public OptionGroup convertToOptionGroup() {
        return new OptionGroup(name, orderOptions.stream().map(OrderOption::convertToOption).collect(toList()));
    }
}
