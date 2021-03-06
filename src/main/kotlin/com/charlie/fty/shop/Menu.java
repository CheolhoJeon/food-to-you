package com.charlie.fty.shop;

import com.charlie.fty.generic.money.Money;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "MENUS")
@Getter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;

    @Column(name = "FOOD_NAME")
    private String name;

    @Column(name = "FOOD_DESCRIPTION")
    private String description;

    @OneToOne
    @JoinColumn(name = "MENU_ID")
    private Shop shop;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MENU_ID")
    private List<OptionGroupSpecification> optionGroupSpecs = new ArrayList<>();

    public Menu(Shop shop, String name, String description, OptionGroupSpecification basic, OptionGroupSpecification... groups) {
        this(null, shop, name, description, basic, Arrays.asList(groups));
    }

    @Builder
    public Menu(Long id, Shop shop, String name, String description, OptionGroupSpecification basic, List<OptionGroupSpecification> additives) {
        this.id = id;
        this.shop = shop;
        this.name = name;
        this.description = description;

        this.shop.addMenu(this);
        this.optionGroupSpecs.add(basic);
        this.optionGroupSpecs.addAll(additives);
    }

    Menu() {
    }

    public Money getBasePrice() {
        return getBasicOptionGroupSpecs().getOptionSpecs().get(0).getPrice();
    }

    private OptionGroupSpecification getBasicOptionGroupSpecs() {
        return optionGroupSpecs
            .stream()
            .filter(spec -> spec.isBasic())
            .findFirst()
            .orElseThrow(IllegalStateException::new);
    }

    public void validateOrder(String menuName, List<OptionGroup> optionGroups) {
        if (!this.name.equals(menuName)) {
            throw new IllegalArgumentException("기본 상품이 변경됐습니다.");
        }

        if (!isSatisfiedBy(optionGroups)) {
            throw new IllegalArgumentException("메뉴가 변경됐습니다.");
        }
    }

    private boolean isSatisfiedBy(List<OptionGroup> cartOptionGroups) {
        return cartOptionGroups.stream().anyMatch(this::isSatisfiedBy);
    }

    private boolean isSatisfiedBy(OptionGroup group) {
        return optionGroupSpecs.stream().anyMatch(spec -> spec.isSatisfiedBy(group));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Shop getShop() {
        return shop;
    }

    public List<OptionGroupSpecification> getOptionGroupSpecs() {
        return optionGroupSpecs;
    }
}
