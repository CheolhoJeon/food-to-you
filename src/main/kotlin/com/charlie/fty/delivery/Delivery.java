package com.charlie.fty.delivery;

import com.charlie.fty.order.Order;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "DELIVERIES")
@Getter
public class Delivery {
    enum DeliveryStatus {DELIVERING, DELIVERED}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DeliveryStatus deliveryStatus;

    public static Delivery started(Order order) {
        return new Delivery(order, DeliveryStatus.DELIVERING);
    }

    public Delivery(Order order, DeliveryStatus deliveryStatus) {
        this.order = order;
        this.deliveryStatus = deliveryStatus;
    }

    Delivery() {
    }

    public void complete() {
        this.deliveryStatus = DeliveryStatus.DELIVERED;
    }
}
